(ns alfa.core
  (:require
    [immutant.web :as web]
    [alfa.routes :as routes]
    [ring.middleware.reload :as re]
    [ring.middleware.defaults :as defaults]
    [ring.middleware.resource :refer [wrap-resource]]
    [noir.cookies :as cookies]))

(def app
  (-> routes/all-routes
      (cookies/wrap-noir-cookies*)
      (defaults/wrap-defaults defaults/site-defaults)
      (re/wrap-reload)))


(def config
  {:port 3000 :host "localhost"})

(defn start
  [& [opts]]
  (web/run-dmc app (or opts config)))

(defn stop
  [& [opts]]
  (web/stop (or opts config)))

(defn restart
  [& [opts]]
  (stop)
  (start))


