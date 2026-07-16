(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest asuncion-has-spec-basis
  (let [sb (facts/spec-basis "asuncion")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://www.asuncion.gov.py/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "ciudad-del-este")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["asuncion" "ciudad-del-este"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ciudad-del-este"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["asuncion.direccion-transparencia-anticorrupcion"]
         (mapv :ordinance/id (facts/by-topic "asuncion" :transparency))))
  (is (empty? (facts/by-topic "asuncion" :labor)))
  (is (empty? (facts/by-topic "ciudad-del-este" :environment))))
