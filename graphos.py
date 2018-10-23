mapa={'A':[0,50,0,50,70], 'B':[0,0,40,0,0], 'C':[0,0,0,40,20],'D':[0,0,40,0,80],'E':[0,30,0,0,0]} 

class GPS(object):
    '''
    Objeto GPS - fornece informações de rota à partir de um mapa definido, o mapa é um tipo dicionário com X componentes 
    com resultado de um vetor de tamanho X, definido da seguinte forma:
    'Origem'=[destino_A, destino_B, destino_C, destino_D, destino_E]
    '''

    # O GPS é instanciado com um mapa
    def __init__(self, mapa):
        self.mapa = mapa
        self.pontos_do_mapa = list()
        self.rotas=list()
        for i in self.mapa.keys():
            self.pontos_do_mapa.append(i)
        self.lista_caminhos()
        self.lista_rotas()

            
    def lista_caminhos(self):
        """ Lista caminhos iniciais disponíveis """
        self.caminhos=list()
        for i in self.pontos_do_mapa:
            for j in self.pontos_do_mapa:
                dist=self.distancia_dois_pontos(i,j)
                if dist!=0:
                    self.caminhos.append(i+j)
    
    def lista_rotas(self):
        """ Lista todas rotas disponíveis """
        for i in self.caminhos:
            for j in self.pontos_do_mapa:
                dist=self.distancia_dois_pontos(i[-1],j)
                if dist!=0:
                    self.rotas.append(i+j)

        for i in range(7):
            a=self.rotas
            b=list()
            for i in a:
                for j in self.pontos_do_mapa:
                    dist=self.distancia_dois_pontos(i[-1],j)
                    if dist!=0:
                        b.append(i+j)
            self.rotas=b

    def distancia_rota(self, rota):
        """ Calcula a distância total em uma rota """
        tamanho=0
        for i in range(len(rota)):
            if i!=0:
                a=self.distancia_dois_pontos(rota[i-1], rota[i])
                if a==0:
                    print("Rota inválida!")
                    tamanho=0
                    break
                else:
                    tamanho+=a
        return tamanho

    def distancia_dois_pontos(self, origem, destino):
        """ Informa a distancia direta entre dois pontos """
        return self.mapa[origem][self.pontos_2_indice(destino)]

    def pontos_2_indice(self, parada):
        for i in range(len(self.pontos_do_mapa)):
            if self.pontos_do_mapa[i]==parada:
                i
                break
        return i

    def existe_ponto(self, ponto):
        """ Verifica se o ponto existe no mapa """
        retorno=False
        for i in self.pontos_do_mapa:
            if i==ponto:
                retorno=True
        return retorno
    
    def rotas_possiveis_dois_pontos(self, origem, destino, parada=""):
        if not(self.existe_ponto(origem)):
            print("Origem não existe!!")
            return set()
        if not(self.existe_ponto(destino)):
            print("Destino não existe!!")
            return set()
        rotas_possiveis=set()
        for i in self.rotas:
            inicio=-1
            fim=-1
            for j in range(len(i)):
                if (i[j]==origem and inicio==-1):
                    inicio=j
                    if origem==destino:
                        j+=1
                elif i[j]==destino:
                    fim=j
                    break
            if (inicio>=0 and fim>=0 and inicio!=fim):
                if parada in i[inicio:fim+1]:
                    rotas_possiveis.add(i[inicio:fim+1])

        if rotas_possiveis==set():
            print(f"Não existem rotas possíveis entre {origem} e {destino} por esse caminho!!")
        elif parada=="":
            print(f"Rotas possíveis entre {origem} e {destino} são: {rotas_possiveis}")
        elif parada!="":
            print(f"Rotas possíveis entre {origem} e {destino} com parada em {parada} são: {rotas_possiveis}")

        return rotas_possiveis

    def menor_distancia_parada(self, origem, destino, parada=""):
        rotas_possiveis = self.rotas_possiveis_dois_pontos(origem, destino, parada)
#        dist_rotas_possiveis=list()
        if rotas_possiveis==set():
            return
#        if parada!="":
        rotas_possiveis=[i for i in rotas_possiveis]
        menor_rota=0
        menor_caminho=""
        for i in rotas_possiveis:
            if parada in i:
                dist=self.distancia_rota(i)
#                print(i,dist)
                if dist>0:
                    if menor_rota==0 or menor_rota>dist:
                        menor_rota=dist
                        menor_caminho=i
        
        if parada=="":
            print(f"Menor Caminho entre {origem} e {destino} é '{menor_caminho}' com distância de {menor_rota}")
        else:
            print(f"Menor Caminho entre {origem} e {destino} com parada em {parada} é '{menor_caminho}' com distância de {menor_rota}")

        
                        
                
                
        
    
meu_gps = GPS(mapa)
#a=meu_gps.menor_distancia_parada("C","B", "E")
#a=meu_gps.rotas_possiveis_dois_pontos("C","B")
