(ns realfa.routes
  (:require
    [compojure.core :refer [routes GET POST context]]
    [compojure.route :refer [not-found resources]]
    [selmer.parser :refer [render-file]]
    [realfa.exp.soal :as soal]))

(selmer.parser/cache-off!)

(defn home-page
  []
  (render-file "public/index.html" {}))

(defn soal-page
  ([] (render-file "public/form.html"
                (soal/show-soal)))
  ([message] (render-file "public/form.html"
                          (merge (soal/show-soal)
                                 {:message message}))))

(defn all-routes
  []
  (routes
    (GET "/" req (home-page))
    (GET "/bobo/:ho" req
      (str (:params req)))
    (POST "/bobo/:ho" req
      (str (:params req)))
    (context "/soal" req
      (GET "/" req (soal-page))
      (POST "/" req
        (println (str (:params req)))
        (let [check (soal/check-answer (:params req))]
          (soal-page (:message check)))))
    (resources "public/")
    (not-found "not found")))






