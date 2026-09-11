(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest asuncion-has-culture-basis
  (let [sb (facts/spec-basis "asuncion")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "asuncion" (:culture/municipality %)) sb))
    (is (every? #(= "PRY" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "ciudad-del-este")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["asuncion" "ciudad-del-este"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ciudad-del-este"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 5 (count (facts/by-kind "asuncion" :dish))))
  (is (= ["asuncion.beverage.terere"]
         (mapv :culture/id (facts/by-kind "asuncion" :beverage))))
  (is (empty? (facts/by-kind "asuncion" :product)))
  (is (empty? (facts/by-kind "ciudad-del-este" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
