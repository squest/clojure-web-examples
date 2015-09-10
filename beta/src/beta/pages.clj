(ns beta.pages
  (:require
    [selmer.parser :refer [render-file]]))

(selmer.parser/cache-off!)

(def articles-list
  (atom []))

(defn tdir
  [fname]
  (str "html/" fname ".html"))

(defn add-article!
  [article-map]
  (let [articles @articles-list
        len (count articles)]
    (->> (assoc
           article-map
           :link
           (str "/article-content/" len))
         (swap! articles-list conj))))

(defn get-article
  [id]
  (let [data (nth @articles-list id)]
    (render-file (tdir "article")
                 data)))

(defn home
  ([] (home "World"))
  ([nama] (render-file (tdir "base")
                       {:nama nama})))

(defn read-request
  [request]
  (render-file (tdir "request")
               {:req request}))

(defn pecah
  [title]
  (render-file (tdir "content-satu")
               {:title title}))

(defn form
  []
  (render-file (tdir "form")
               {}))

(defn articles
  []
  (render-file (tdir "articles")
               {:articles @articles-list}))