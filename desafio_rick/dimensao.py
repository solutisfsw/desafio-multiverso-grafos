class Dimensao:

    def __init__(self):
        self.nome = None
        self.rotas = None
        self.ativo = True

    def set_nome(self, nome):
        self.nome = nome

    def get_nome(self):
        return self.nome

    def set_rotas(self, rotas):
        self.rotas = rotas

    def get_rotas(self):
        return self.rotas

    def set_ativo(self, ativo):
        self.ativo = ativo

    def get_ativo(self):
        return self.ativo