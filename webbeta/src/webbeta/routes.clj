(ns webbeta.routes
  (:require
    [compojure.core :as compojure :refer [routes GET POST context]]
    [compojure.route :refer [not-found resources]]
    [webbeta.pages :as page]))

(defn all-routes
  []
  (routes
    (GET "/" req
      (page/home))
    (GET "/article" req
      (page/articles))
    (GET "/article/:id" [id]
      (page/article id))
    (POST "/" req "Wowowo")
    (resources "public/")
    (not-found "Not found")))


