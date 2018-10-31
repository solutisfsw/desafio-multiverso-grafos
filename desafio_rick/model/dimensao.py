class Dimensao:

    def __init__(self):
        self._nome = None
        self._rotas = None
        self._ativo = True

    def set_nome(self, nome):
        self._nome = nome

    def get_nome(self):
        return self._nome

    def set_rotas(self, rotas):
        self._rotas = rotas

    def get_rotas(self):
        return self._rotas

    def set_ativo(self, ativo):
        self._ativo = ativo

    def get_ativo(self):
        return self._ativo