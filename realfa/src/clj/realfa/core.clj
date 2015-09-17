(ns realfa.core
  (:require
    [org.httpkit.server :as http]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [noir.cookies :as cookies]
    [realfa.routes :refer [all-routes]]))

(defonce server (atom nil))

(defn handler
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (let [uri (:uri req)]
              (cond (= uri "/") (realfa.routes/home-page)
                    (= uri "/soal") "SOAL"
                    :else "GA ADA APAPA"))})

(defn start
  ([] (start 3000))
  ([port] (->> (-> (all-routes)
                   (cookies/wrap-noir-cookies*)
                   (wrap-defaults (update-in site-defaults
                                             [:security :anti-forgery]
                                             #(not %)))
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

