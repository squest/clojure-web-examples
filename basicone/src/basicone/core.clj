(ns basicone.core
  (:require
    [org.httpkit.server :as http]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [basicone.routes :refer [all-routes]]))

(defonce server (atom nil))

;; web application => routes => middlewares
;; http-request => process => http-response
;; list of functions => http-response

(def app
  (wrap-defaults all-routes site-defaults))

(def config {:port 3000})

(defn start [] (reset! server (http/run-server app config)))

(defn stop [] (@server))

(defn restart [] (stop) (start))



