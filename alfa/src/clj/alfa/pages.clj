(ns alfa.pages
  (:require
    [selmer.parser :refer [render-file]]))

(defn tdir
  [fname]
  (str "public/" fname ".html"))

(defn home-page
  [& args]
  (let [nama (first args)]
    (render-file
      (tdir "index")
      {:nama (or nama "World")})))
