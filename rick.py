import sys
import datetime
import numpy as np

#para output de verificação
debug = False
#desconsiderar os pontos de partida e chegada como paradas 
ajusteParadas = 2

#metodo recursivo que buscaas possibilidades de rotas a partir de um ponto de partida inicial
def _buscaRotas_(o, d, dists, distancia, oorig, passagens, rotas, permiteRetorno):
    for i, dist in dists.iterrows():
        if (dist['O'] == o):

            #quando a situacao preve retorno para o ponto de partida e testa se alcançou o ponto esperado
            if ((permiteRetorno) & (dist['D'] == oorig)):
                fpassagens = np.append( passagens, [ dist['D'] ] )
                chegada = distancia + dist['DT']
                #adiciona ao array de rotas os pontos de passagens, a distancia percorrida e o numero de paradas
                rotas.append( [fpassagens, chegada, len(fpassagens) - ajusteParadas] )
                if (debug): print( "   CHEGOU ",  dist['O'], " PARA ", dist['D'], " :: ",  chegada)
                
            #detecta se o algoritmo ja passou pelo ponto sendo testado, para detectar um loop e parar 
            elif ((dist['D'] in passagens) > 0):
                if (debug): print( "   EM LOOP  ",  dist['O'], " PARA ",   dist['D'] )
                
            #detecta se chegou ao ponto de destino 
            elif (dist['D'] == d):
                fpassagens = np.append( passagens, [ dist['D'] ] )
                chegada = distancia + dist['DT']
                #adiciona ao array de rotas os pontos de passagens, a distancia percorrida e o numero de paradas
                rotas.append( [fpassagens, chegada, len(fpassagens) - ajusteParadas] )
                if (debug): print( "   CHEGOU ",  dist['O'], " PARA ", dist['D'], " :: ",  chegada)
                
            #caso nao tenha ocorrido nenhuma das situacoes anteriores, avanca para o proximo ponto 
            else:
                rdistancia = distancia + dist['DT']
                if (debug): print( "   PROXIMO ",  dist['O'], " PARA ", dist['D'], " :: ",  rdistancia )
                tpassagens = np.array([])
                tpassagens = np.append( tpassagens, [ passagens ] )
                tpassagens = np.append( tpassagens, [ dist['D'] ] )
                rdistancia = _buscaRotas_(dist['D'], d, dists, rdistancia, oorig, tpassagens, rotas, permiteRetorno)

#metodo inicial da busca de rota com origem e destino e a lista de distancia entre os universos
def _inicio_(o, d, dists):
    permiteRetorno = False
    if (o == d):
        permiteRetorno = True
        
    rotas = []
    for i, dist in dists.iterrows():
        if (dist['O'] == o):
            distancia = dist['DT']
            passagens = np.array([])
            passagens = np.append( passagens, [ dist['O'] ] )
            passagens = np.append( passagens, [ dist['D'] ] )
            if (dist['D'] == d):
                rotas.append( [passagens, dist['DT'], 2 - ajusteParadas] )
                if (debug): print( "CHEGOU ",  dist['O'], " PARA ", dist['D'], " :: ", dist['DT'] )
            else:
                if (debug): print( "ROTA POR - ",  dist['D'])
                _buscaRotas_(dist['D'], d, dists, distancia, o, passagens, rotas, permiteRetorno)
            
    if (debug):
        for rota in rotas: print( rota )

    return rotas       

def numeroRotas(distancias, o, d, maxParadas, maxDistancia):
    rotas = _inicio_(o, d, distancias)
    
    count = 0

    sparadas = ""
    if (maxParadas != 0):
        sparadas = ", com no máximo " + "{:.0f}".format(maxParadas) + " paradas,"
    sdistancia = ""
    if (maxDistancia != 0):
        sdistancia = ", com distância máxima " + "{:.0f}".format(maxDistancia) + ","

    count = len(rotas)
    srotas = "";
    for rota, distancia, paradas in rotas:
        if ((maxParadas != 0) & (paradas > maxParadas)):
            count = count - 1
        elif ((maxDistancia != 0) & (distancia > maxDistancia)):
            count = count - 1
        else:
            srotas = srotas + "[" + "-".join(str(x) for x in rota) + "] "

    if (o != d):
        print("O número de rotas entre", o, "e", d, sparadas, sdistancia, "é:")
    else:
        print("O número de rotas saindo de", o, "e voltando a", d, sparadas, sdistancia, "é:")
    print("   ", count, "- Rotas:", srotas)

    print(" ")
    return count, srotas


def menorDistancia(distancias, o, d, passandoPor = []):
    rotas = _inicio_(o, d, distancias)
    
    count = 0

    menorDist = 0
    menorRota = []
    for rota, distancia, paradas in rotas:
        atende = True
        for passando in passandoPor:
            if ((passando in rota) == False):
                atende = False
                  
        if ((atende) & ((menorDist == 0) | (distancia < menorDist))):
            menorDist = distancia
            menorRota = rota

    spassandoPor = ""
    if (len(passandoPor) > 0):
        spassandoPor = ", passando por " + " ".join(str(x) for x in passandoPor) + ","
        
    if (len(menorRota) != 0):
        if (o != d):
            print("A menor rota (em espaço-tempo) entre", o, "e", d, spassandoPor, "é:")
        else:
            print("A menor rota (em espaço-tempo) saindo de", o, "e voltando a", d, spassandoPor, "é:")
        print("   ", menorDist, "- Rota:", "[" + "-".join(str(x) for x in menorRota) + "] ")
    else:
        print("Não existem rotas entre", o, "e", d)
    print(" ")
    return menorDist, menorRota







