(ns webtwo.handler
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [noir.response :as resp]))

(defroutes
  app-routes
  (GET "/" [] "Hello World")
  (GET "/api/:nama/:alamat/:daftarwarisan"
       [nama alamat daftarwarisan]
    (resp/json {:nama nama
                :message (str "Say hello " nama)
                :alamat alamat
                :daftar daftarwarisan}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
