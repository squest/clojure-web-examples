(ns beta.routes
  (:require
    [compojure.core :refer [defroutes routes GET POST]]
    [compojure.route :as route]
    [beta.pages :as page]
    [noir.response :as resp]))

;; http://zenius.net/cg/129/blablabla

;; designing a web api?? => functions => uri/url

;; browser -> http-request -> app -> http-response -> browser

;; http-request -> clojure's hash-map
;; http-response -> clojure's hash-map

;; 8 methods http-requests GET POST PUT DELETE OPTION HEAD

;; http://localhost:3000/ => hello world

(def all-routes
  (routes
    (GET "/" [request]
      (page/home))
    (GET "/daftar" []
      (page/form))
    (GET "/daftar-article" []
      (page/articles))
    (GET "/article-content/:id" [id]
      (let [id-beneran (read-string id)]
        (page/get-article id-beneran)))
    (POST "/add-article" request
      (do (page/add-article! (:params request))
          (resp/redirect "/")))
    (route/resources "public/")
    (route/not-found "Not found error 404")))














