(ns webbeta.core
  (:require
    [org.httpkit.server :as http]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [noir.cookies :as cookies]
    [webbeta.routes :refer [all-routes]]))

(defonce server (atom nil))

(defn start
  ([] (start 3000))
  ([port]
   (->> (-> (all-routes)
            (cookies/wrap-noir-cookies*)
            (wrap-defaults (assoc-in site-defaults
                                     [:security :anti-forgery]
                                     false))
            (http/run-server {:port port}))
        (reset! server))))

(defn stop
  []
  (@server)
  (reset! server nil))

(defn reset
  []
  (stop)
  (Thread/sleep 200)
  (start))
