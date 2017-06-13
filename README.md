Négyet egy sorba
===================
A játékot 6 X 7 rácson játszhatja 2 személy adott számú korongokkal. A játékosok felváltva dobnak be egy korongot a saját színükből a rács valamelyik oszlopába. A korong le fog csúszni az oszlop aljára vagy az oszlopban található legfelső korongig. A játék célja, hogy elhelyezzünk a 4 ugyanolyan színű korongot úgy, hogy azok sor- vagy oszlop folytonosak, illetve átlósan szomszédosak legyenek. Az ellenfelet pedig ugyanebben kell megakadályozni.. 

----------


Adatbázis
-------------

	
	
Adattábla

| Tábla Név| Adattípus|
| :------- | :---- |
| PLAYER_NAME|VARCHAR2(50 BYTE)|
| PLAYER_WINS|NUMBER(10,0)
| PLAYER_LOSES|NUMBER(10,0) 
| PLAYER_TIE| NUMBER(10,0)


----------
Futtatás:

	1. mvn package
	2. mvn exec:java

