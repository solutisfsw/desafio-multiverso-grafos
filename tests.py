import unittest
import rick
import pandas as pd

class testes( unittest.TestCase ):

    def test_distancia_a_c_passando_b( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        distancia, rota = rick.menorDistancia(distancias, "A", "C", ["B"])
        self.assertEqual(distancia, 90)

    def test_distancia_a_d( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        distancia, rota = rick.menorDistancia(distancias, "A", "D")
        self.assertEqual(distancia, 50)
        
    def test_distancia_a_c_passando_e( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        distancia, rota = rick.menorDistancia(distancias, "A", "C", ["E"])
        self.assertEqual(distancia, 140)

    def test_numero_rotas_c_c_3_paradas( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        numrotas, rotas = rick.numeroRotas(distancias, "C", "C", 3, 0)
        self.assertEqual(numrotas, 3)

    def test_numero_rotas_a_c_4_paradas( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        numrotas, rotas = rick.numeroRotas(distancias, "A", "C", 4, 0)
        self.assertEqual(numrotas, 4)

    def test_distancia_a_c( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        distancia, rota = rick.menorDistancia(distancias, "A", "C")
        self.assertEqual(distancia, 90)
        
    def test_distancia_b_b( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        distancia, rota = rick.menorDistancia(distancias, "B", "B")
        self.assertEqual(distancia, 90)
        
    def test_numero_rotas_c_c_300_dist( self ):
        distancias = pd.read_csv("distancias.csv", sep= ',', header=0)
        numrotas, rotas = rick.numeroRotas(distancias, "C", "C", 0, 300)
        self.assertEqual(numrotas, 3)


        
if __name__ == '__main__':
    unittest.main()
