(ns realfa.exp.soal
  (:import [java.util.UUID]))

(def soals
  [{:problem    "Manakah yang dibawah ini?"
    :problem-id 1
    :answers    [["goks" true]
                 ["what" false]
                 ["which" false]
                 ["whateva" false]]}
   {:problem    "Manakah yang di atas ini?"
    :problem-id 2
    :answers    [["goks" true]
                 ["what" false]
                 ["which" false]
                 ["whateva" false]]}])

(defonce token
  (let [uuids (take 20 (repeatedly #(str (java.util.UUID/randomUUID))))]
    (zipmap (map str "ABCDEFGHJIK")
            uuids)))

(defonce tokeni
  (into {} (map #(vec (reverse %)) token)))

(def answer-map
  (zipmap (range 15)
          (map str "ABCDEFGHIJKLMNOP")))

(defn show-soal
  ([] (show-soal (rand-nth soals)))
  ([soal] (let [{:keys [answers]} soal
                soal-answers (shuffle answers)
                right-answer (->> (mapv #(vector (second %)) soal-answers)
                                  (map-indexed #(conj %2 %1))
                                  (filter first)
                                  first second)]
            (merge soal
                   {:answers        (mapv #(merge {}
                                                  {:option %1
                                                   :string %2})
                                          (map str "ABCDEFGHIJ")
                                          (map first soal-answers))
                    :tokenfn (token (answer-map right-answer))}))))

(defn check-answer
  [user-answer]
  (let [{:keys [answer tokenfn]} user-answer]
    (if (= answer (tokeni tokenfn))
      {:status true :message "Yeay jawaban bener!"}
      {:status false :message "GOBLOK, COBA LAGI!"})))




