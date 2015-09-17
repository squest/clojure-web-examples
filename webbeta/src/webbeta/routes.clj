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
    (GET "/wala" req
      (page/home "Wala"))
    (POST "/" req "Wowowo")
    (resources "public/")
    (not-found "Not found")))


