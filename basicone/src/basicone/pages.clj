(ns basicone.pages
  (:require
    [selmer.parser :refer [render-file]]))

(defn home-page
  [bisa-request-bisa-nama]
  (render-file "template/base.html"
               {:nama    bisa-request-bisa-nama
                :request bisa-request-bisa-nama}))

(defn indon-page
  [somestring]
  (render-file "template/indon.html"
               {:popo somestring}))

(defn soal-page
  ([] (let [data (-> (slurp "http://localhost:8080/")
                     (read-string))]
        (render-file "template/soal.html"
                     {:problem (:problem-string data)
                      :kode (:kode data)
                      :answer  (:answer data)})))
  ([kode]
   (let [data (-> (slurp (str "http://localhost:8080/kode/" kode))
                  (read-string))]
     (render-file "template/soal.html"
                  {:problem (:problem-string data)
                   :kode (:kode data)
                   :answer  (:answer data)}))))

(def base-host "http://localhost:8080/")

(defn all-soal
  []
  (let [data (-> (slurp (str base-host "all-soal"))
                 read-string)]
    (render-file "template/daftar.html"
                 {:soals data})))

(defn something
  []
  (->> (slurp "https://projecteuler.net/problem=8")
       (cs/split-lines)
       (drop 56 )
       (take 20)
       (mapcat #(apply str (take 50 %)))
       (map #(str %))
       (map read-string)))





















