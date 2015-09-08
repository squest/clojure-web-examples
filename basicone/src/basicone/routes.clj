(ns basicone.routes
  (:require
    [compojure.core :refer [routes GET POST]]
    [compojure.route :refer [resources not-found]]
    [basicone.pages :as page]))

(def main-routes
  (routes
    (GET "/" request
      (page/home-page (str request)))
    (GET "/sayhi/:nama" [nama]
      (page/home-page nama))
    (GET "/randomsoal/" []
      (page/soal-page))
    (GET "/soal/:kode" [kode]
      (page/soal-page kode))
    (GET "/daftar-soal/" []
      (page/all-soal))
    (GET "/pilihan/:pil" [pil]
      (cond
        (= pil "masyarakat") (page/home-page "world")
        (= pil "indon") (page/indon-page "Woi indon!")
        :else (page/home-page "Hai der!")))))

(def misc-routes
  (routes
    (resources "/public")
    (not-found "Not found boy!")))

(def all-routes
  (routes main-routes misc-routes))













