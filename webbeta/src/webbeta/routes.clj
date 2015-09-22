(ns webbeta.routes
  (:require
    [compojure.core :as compojure :refer [routes GET POST context]]
    [compojure.route :refer [not-found resources]]
    [webbeta.pages :as page]
    [webbeta.articles :as article]
    [noir.response :as response]))

(defn all-routes []
  (routes
    (GET "/" req
      (page/home))
    (GET "/article/:id" req
      (let [id (get-in req [:params :id])]
        (page/article id)))
    (GET "/add-article" req
      (page/add-article))
    (POST "/add-article" req
      (let [new-article (get-in req [:params])]
        (article/add-article new-article)
        (response/redirect "/")))
    (resources "public/")
    (not-found "Not found")))


