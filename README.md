# **SIMULADOR DE ALGORITMOS DE SUBSTITUIÇÃO DE PÁGINAS**  
*(Sistemas Operacionais - Ciência da Computação - Universidade de Fortaleza)*  

**AUTOR 1:** Eduardo Jorge Andrade Mourão Oliveira
**AUTOR 2:** Renan Elid Soares

**PALAVRAS-CHAVE:** Memória Virtual. Algoritmos de Substituição. FIFO. LRU. Clock. Aging.  

---

## **RESUMO**  
Este trabalho propõe o desenvolvimento de um **simulador de algoritmos de substituição de páginas** em sistemas de gerenciamento de memória virtual, implementado em **Java**. O simulador compara o desempenho de quatro algoritmos clássicos (**FIFO, LRU, Clock e Aging**) em diferentes cenários de acesso à memória, medindo o número de **faltas de página (page faults)** e apresentando gráficos comparativos. Os resultados demonstram como cada algoritmo se comporta diante de sequências de referência variadas, fornecendo insights valiosos para a escolha da estratégia mais eficiente em sistemas operacionais reais. 

---

## **INTRODUÇÃO**  
A **memória virtual** é um componente crítico nos sistemas operacionais modernos, permitindo que processos executem mesmo quando não há espaço físico suficiente na RAM. Para gerenciar essa memória, algoritmos de substituição de páginas decidem **qual página remover** quando ocorre um **page fault**.  

Neste projeto, implementamos um **simulador** que avalia quatro algoritmos:  
1. **FIFO (First-In, First-Out)**  
2. **LRU (Least Recently Used)**  
3. **Clock (Segunda Chance)**  
4. **Aging (Envelhecimento)**  

O objetivo é **comparar estatisticamente** seu desempenho e entender em quais cenários cada um se destaca.  

---

### **Tecnologias Utilizadas**  
- **Linguagem:** Java (JDK 17+)  
- **Interface Gráfica:** Swing (para visualização interativa)  
- **Gráficos:** JFreeChart 1.5.3 e JCommon 1.0.24  
- **Controle de Versão:** Git/GitHub  

### **Estrutura do Projeto**

* `lib/` – Bibliotecas externas
    * `jcommon-1.0.24.jar`
    * `jfreechart-1.5.3.jar`
* `src/`
    * `controller/` – Lógica de controle
        * `SimulationController.java`
    * `model/` – Algoritmos e simulação
        * `algorithms/`
            * `Aging.java`
            * `Clock.java`
            * `FIFO.java`
            * `LRU.java`
            * `PageReplacement.java`
        * `MemorySimulation.java`
    * `view/` – Interface gráfica
        * `MainFrame.java`
        * `App.java`

### **Compilação e Execução**  

#### **Pré-requisitos**  
- JDK 17+  
- JFreeChart 1.5.3  
- JCommon 1.0.24  

#### **Para Linux/Mac**

```bash
# Instalar as duas bibliotecas extras utilizadas
wget https://repo1.maven.org/maven2/org/jfree/jfreechart/1.5.3/jfreechart-1.5.3.jar  
wget https://repo1.maven.org/maven2/org/jfree/jcommon/1.0.24/jcommon-1.0.24.jar  

# Compilar a aplicação
javac -cp ".:lib/jfreechart-1.5.3.jar:lib/jcommon-1.0.24.jar" \
    src/App.java \
    src/view/MainFrame.java \
    src/controller/SimulationController.java \
    src/model/MemorySimulation.java \
    src/model/algorithms/*.java \
    -d bin

# Executar
java -cp "bin:lib/jfreechart-1.5.3.jar:lib/jcommon-1.0.24.jar" App
```

## **Para Windows**

### **Baixe os arquivos**:

- [jfreechart-1.5.3.jar]()
- [jcommon-1.0.24.jar]()

### **Compile e execute**:

Abra o terminal `cmd` no diretório do projeto e execute:

```bash
javac -cp ".;jfreechart-1.5.3.jar;jcommon-1.0.24.jar" src\App.java src\view\MainFrame.java src\controller\SimulationController.java src\model\MemorySimulation.java src\model\algorithms\*.java -d bin
```

### **Utilização do Simulador**
#### **Passos para Simular**:

1. Insira a sequência de páginas
- Exemplo: 1, 2, 3, 4, 1, 2, 5, ...

2. Defina o número de frames de memória

3. Clique em "Executar Simulação"
-  Exemplo: 3

4. Visualize os resultados nas abas:

- Resultados: Dados numéricos

- Gráfico: Comparação visual

- Descrição: Explicação dos algoritmos


### **Algoritmos Implementados**  
| Algoritmo  | Descrição | Complexidade |  
|------------|-----------|--------------|  
| **FIFO**   | Substitui a página mais antiga | O(1) |  
| **LRU**    | Substitui a página menos usada recentemente | O(n) |  
| **Clock**  | Versão aproximada do LRU com bit de referência | O(1) |  
| **Aging**  | LRU em software usando contadores | O(n) |  

---

## **RESULTADOS E DISCUSSÃO**  
### **Cenários de Teste**  
- **Sequência de Referência**: 1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5  
- **Número de Molduras (Frames)**: 3

#### **Resultados Obtidos**
| Algoritmo                   | Faltas de Página |
|----------------------------|------------------|
| FIFO (First-In, First-Out) | 9                |
| LRU (Least Recently Used)  | 10               |
| Clock (Second Chance)      | 9                |
| Aging                      | 10               |

---

#### **Discussão dos Resultados**

A simulação compara o desempenho de quatro algoritmos clássicos de substituição de páginas sob a mesma sequência de referências, utilizando três molduras de memória. A métrica principal avaliada é o número de **page faults** (faltas de página), que indicam quantas vezes foi necessário buscar uma página na memória secundária por não estar presente na memória principal.

- **FIFO (First-In, First-Out)**:  
  Apresentou **9 faltas de página**. Este algoritmo substitui a página que está há mais tempo na memória, independentemente de seu uso recente. Embora seja simples de implementar, pode sofrer com a **anomalia de Belady**, em que o aumento de molduras não reduz necessariamente as faltas de página — o que pode ser observado aqui, já que sua performance foi inferior à do LRU em alguns pontos da sequência.

- **LRU (Least Recently Used)**:  
  Obteve o maior número de faltas (**10**), embora geralmente ofereça melhor desempenho teórico por substituir a página **menos recentemente utilizada**. Sua implementação é mais complexa, muitas vezes exigindo suporte adicional via hardware ou consumo de recursos por meio de software. O desempenho ligeiramente inferior neste cenário específico pode estar relacionado à presença de **padrões de acesso repetitivos**, nos quais o FIFO e o Clock, curiosamente, se saem melhor.

- **Clock (Segunda Chance)**:  
  Também apresentou **9 faltas de página**, igualando-se ao FIFO. O Clock é uma **implementação prática** que simula o LRU com menor complexidade, utilizando um bit de referência. Ele oferece um **equilíbrio entre desempenho e simplicidade**, sendo especialmente útil em sistemas operacionais reais.

- **Aging**:  
  Assim como o LRU, também registrou **10 faltas de página**. É uma alternativa ao LRU que emprega **contadores de envelhecimento**, tornando-o ideal para sistemas sem suporte de hardware para LRU. A performance idêntica à do LRU nesta simulação confirma sua eficácia como substituto em ambientes com **restrições de recursos computacionais**.

---
  

### **Gráfico Comparativo**  
![img](/assets/grafico-comparativo.png)
---

## **CONCLUSÃO**  
O simulador desenvolvido permitiu **avaliar empiricamente** o desempenho dos algoritmos de substituição de páginas, confirmando que:  
1. **FIFO** é simples, mas ineficiente em certos cenários.  
2. **LRU** é teoricamente ótimo, mas custoso para implementar.  
3. **Clock e Aging** oferecem um bom equilíbrio entre desempenho e viabilidade prática.  

Sugere-se o uso do **Clock** em sistemas com suporte a hardware e **Aging** em sistemas puramente em software.

---

## **REFERÊNCIAS**  
1. Documentação Oracle Java Swing: [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/).
2. JFreeChart. [Documentação Oficial](https://sourceforge.net/projects/jfreechart/)  

Link para o repositório:
 - [atividade-simulador]

---