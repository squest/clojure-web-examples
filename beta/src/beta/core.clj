(ns beta.core
  (:require
    [org.httpkit.server :as http]
    [clojure.string :as cs :refer [split-lines]]
    [beta.routes :refer [all-routes]]
    [ring.middleware.defaults :as defaults]))

(def app
  (-> all-routes
      (defaults/wrap-defaults defaults/site-defaults)))

(defonce server (atom nil))

(defn start
  []
  (reset! server (http/run-server app {:port 3000})))

(defn stop
  []
  (@server)
  (reset! server nil))

(defn reset
  []
  (stop)
  (start))

(defn -main
  [& args]
  (start))




















