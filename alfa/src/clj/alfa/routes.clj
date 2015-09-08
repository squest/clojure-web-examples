(ns alfa.routes
  (:require
    [compojure.core :refer [routes defroutes GET POST]]
    [compojure.route :as route]
    [noir.cookies :as cookies]
    [alfa.pages :as page]))

(def main-routes
  (routes
    (GET "/" []
      (page/home-page))))

(def misc-routes
  (routes
    (route/resources "/public")
    (route/not-found "Kagak ada apa apa an")))

(def all-routes
  (routes main-routes misc-routes))
