import rick
import pandas as pd

distancias = pd.read_csv("distancias.csv", sep= ',', header=0)


rick.menorDistancia(distancias, "A", "C", ["B"]);

rick.menorDistancia(distancias, "A", "D")

rick.menorDistancia(distancias, "A", "C", ["E"]);

rick.numeroRotas(distancias, "C", "C", 3, 0)

rick.numeroRotas(distancias, "A", "C", 4, 0)

rick.menorDistancia(distancias, "A", "C")

rick.menorDistancia(distancias, "B", "B")

rick.numeroRotas(distancias, "C", "C", 0, 300)
