class Rota:

    def __init__(self, distancia, dimensao):
        self._distancia = distancia
        self._dimensao = dimensao

    def get_distancia(self):
        return self._distancia

    def get_dimensao(self):
        return self._dimensao