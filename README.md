# Chameleon clustering algorithm

<b>Kompilacja:</b><br><br>

javac Combiner.java<br>

<b>Uruchomienie:</b><br>

Program należy uruchomić podając mu kolejno 4 argumenty:<br>

• Nazwa pliku z danymi wejściowymi (wraz z rozszerzeniem)<br>
• Parametr k (wartość liczbowa)<br>
• Początkowa liczba klastrów (wartość liczbowa)<br>
• Oczekiwana liczba klastrów na wyjściu programu (wartość liczbowa)<br>

<b>Przykład:</b><br><br>
java Combiner myFileName.csv 3 15 3

<b>Skompilowany .jar jest w lokalizacji:</b><br><br>
Folder dataset w którym będą zbiory .csv powinien być w tej samej lokalizacji w której plik SPDB.jar <br>

 <b>Plik .jar jest w lokalizacji: </b>/SPDB_jar/SPDB.jar<br>
 <b>Plik z danymi do wywołania jest w lokalizacji: </b>/SPDB_jar/dataset/utah_ariz_newMexico_colorado.csv<br>
 
 java -jar SPDB.jar utah_ariz_newMexico_colorado.csv 4 8 4 
 
 
