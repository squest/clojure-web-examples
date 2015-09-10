(ns beta.pages
  (:require
    [selmer.parser :refer [render-file]]))

(defn home
  []
  (render-file "html/base.html"
               {}))


