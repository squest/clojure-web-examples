(ns banksoal.core
  (:require
   [com.ashafa.clutch :as cl]
   [selmer.parser :refer [render]]
   [org.httpkit.server :refer [run-server]]
   [compojure.core :refer [routes GET POST]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
   [noir.response :as response]))

(defonce server (atom nil))

(def cdb "banksoal")

(defn get-soal
  ([] (rand-nth (map :value (cl/get-view cdb "soal" "byId"))))
  ([kode-soal]
     (->> {:key kode-soal}
          (cl/get-view cdb "soal-berkode" "byKode")
          first :value)))

(defn all-soal
  []
  (->> (cl/get-view cdb "soal-berkode" "byKode")
       (map :value)
       (map :kode)))

(def all-routes
  (routes
   (GET "/" [] (response/edn (get-soal)))
   (GET "/all-soal" []
        (response/edn (all-soal)))
   (GET "/kode/:kode" [kode]
        (response/edn (get-soal (read-string kode))))))

(def app
  (wrap-defaults all-routes site-defaults))

(defn start
  []
  (reset! server (run-server app {:port 8080})))

(defn stop
  []
  (@server)
  (reset! server nil))

(defn restart
  []
  (stop)
  (start))

(defn soal
  []
  (for [m (range 5 20)
        a (range 5 20)]
    {:m m :a a :f (* m a)}))

(def kode-soal (atom 0))

(defn masukin-soal-berkode
  []
  (doseq [content (soal)]
    (cl/put-document
     cdb
     {:type "soal-berkode"
      :kode (do (swap! kode-soal inc)
                @kode-soal)
      :problem-string (render "Jika m = {{m}} dan a = {{a}} berapa f? "
                              {:m (:m content)
                               :a (:a content)})
      :answer (:f content)})))

(defn masukin-soal
  []
  (doseq [content (soal)]
    (cl/put-document
     cdb
     {:type "soal"
      :problem-string (render "Jika m = {{m}} dan a = {{a}} berapa f? "
                              {:m (:m content)
                               :a (:a content)})
      :answer (:f content)})))




