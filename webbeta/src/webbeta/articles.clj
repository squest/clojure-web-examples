(ns webbeta.articles)


(def articles (atom (->> "resources/data/article.edn" slurp read-string)))

(defn all-articles
  []
  (->> @articles
       (map #(dissoc % :text))))

(defn article
  [id]
  (first (filter #(= (:id %) id) @articles)))

(defn add-article
  [new-article]
  (->> (assoc new-article
         :id (str (inc (count @articles))))
       (conj @articles)
       (spit "resources/data/article.edn"))
  (reset! articles (->> "resources/data/article.edn" slurp read-string)))
