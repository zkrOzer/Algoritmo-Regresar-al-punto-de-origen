(ns plffinal.core-test
  (:require [clojure.test :refer :all]
            [plffinal.core :refer :all]))


(deftest regresa-al-punto-de-origen?-test
  (testing "prueba de regresa al punto de origen"

    (is (= true (regresa-al-punto-de-origen? "")))
    (is (= true (regresa-al-punto-de-origen? [])))
    (is (= true (regresa-al-punto-de-origen? (list))))

    (is (= true (regresa-al-punto-de-origen? "><")))
    (is (= true (regresa-al-punto-de-origen? "v^")))
    (is (= true (regresa-al-punto-de-origen? (list \> \<))))
    (is (= true (regresa-al-punto-de-origen? [\v \^])))
    (is (= true (regresa-al-punto-de-origen? "^>v<")))
    (is (= true (regresa-al-punto-de-origen? (list \^ \> \v \<))))
    (is (= true (regresa-al-punto-de-origen? "<<vv>>^^")))
    (is (= true (regresa-al-punto-de-origen? [\< \< \v \v \> \> \^ \^])))

    (is (= false (regresa-al-punto-de-origen? ">")))
    (is (= false (regresa-al-punto-de-origen? (list \>))))
    (is (= false (regresa-al-punto-de-origen? "<^")))
    (is (= false (regresa-al-punto-de-origen? [\< \^])))
    (is (= false (regresa-al-punto-de-origen? ">>><<")))
    (is (= false (regresa-al-punto-de-origen? (list \> \> \> \< \<))))
    (is (= false (regresa-al-punto-de-origen? [\v \v \^ \^ \^])))))


(deftest regresan-al-punto-de-origen?-test
  (testing "prueba de regresan al punto de origen?"

    (is (= true (regresan-al-punto-de-origen? (vector []))))
    (is (= true (regresan-al-punto-de-origen? (vector ""))))
    (is (= true (regresan-al-punto-de-origen? (vector [] "" (list)))))
    (is (= true (regresan-al-punto-de-origen? (vector "" "" "" "" [] [] [] (list) ""))))
    (is (= true (regresan-al-punto-de-origen? (vector ">><<" [\< \< \> \>] (list \^ \^ \v \v)))))

    (is (= false (regresan-al-punto-de-origen? (vector (list \< \>) "^^" [\> \<]))))
    (is (= false (regresan-al-punto-de-origen? (vector ">>>" "^vv^" "<<>>"))))
    (is (= false (regresan-al-punto-de-origen? (vector [\< \< \> \> \> \> \> \> \> \>]))))))


  (deftest regreso-al-punto-de-origen?-test
    (testing "prueba de regreso al punto de origen?"

      (is (= (list) (regreso-al-punto-de-origen "")))
      (is (= (list \v \v \v \< \> \^ \^ \^)(regreso-al-punto-de-origen (list \^ \^ \^ \> \< \v \v \v))))
      (is (= (list \< \< \<) (regreso-al-punto-de-origen ">>>")))
      (is (= (list \> \^ \^ \^ \< \<) (regreso-al-punto-de-origen [\< \v \v \v \> \>])))))
  

  (deftest mismo-punto-final?-test
    (testing "prueba de mismo punto final??"

      (is (= true (mismo-punto-final? "" [])))
      (is (= true (mismo-punto-final? "^^^" "<^^^>")))
      (is (= true (mismo-punto-final? [\< \< \< \>] (list \< \<))))
      (is (= true (mismo-punto-final? (list \< \v \>) (list \> \v \<))))

      (is (= false (mismo-punto-final? "" "<")))
      (is (= false (mismo-punto-final? [\> \>] "<>")))
      (is (= false (mismo-punto-final? [\> \> \>] [\> \> \> \>])))
      (is (= false (mismo-punto-final? (list) (list \^))))))
  
  (deftest coincidencias-test
    (testing "prueba de coincidencias"

      (is (= 1 (coincidencias (apply str "" []))))
      (is (= 1 (coincidencias  (apply str (list \< \<) [\> \>]))))

      (is (= 4 (coincidencias (apply str "<<vv>>^>>" "vv<^"))))
      (is (= 6 (coincidencias (apply str ">>>>>" [\> \> \> \> \>]))))
      (is (= 6 (coincidencias (apply str [\> \> \> \> \>] (list \> \> \> \> \> \> \^ \^ \^ \^)))))))

(run-tests)