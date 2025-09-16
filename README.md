# Sistema de Sacola de Compras

Este projeto foi desenvolvido como atividade prática da disciplina de Padrões de Projeto com Java. O objetivo é criar um sistema de Sacola de Compras que simula diferentes tipos de pagamento, aplicando os padrões Facade e Flyweight para organizar e otimizar o código.

## Enunciado da Atividade

Crie um sistema do tipo Sacola de Compras, que deve conter módulos para simular tipos de pagamento (cartão, boleto, pix e espécie). Aos processos que apresentam uma complexidade razoável, deve-se adotar uma fachada (facade) para as chamadas como ato do pagamento. Como temos vários usuários fazendo compras com produtos repetidos, o padrão Flyweight deve ser utilizado para otimizar o uso de memória.

## Padrões de Projeto Utilizados

### Facade
O padrão Facade é utilizado para simplificar o processo de pagamento, centralizando as chamadas e ocultando a complexidade dos diferentes métodos de pagamento. A classe `PaymentFacade` oferece uma interface única para processar pagamentos, tornando o uso do sistema mais simples e desacoplado.

### Flyweight
O padrão Flyweight é aplicado na gestão dos produtos. A classe `ProductFactory` garante que produtos com o mesmo código compartilhem a mesma instância, economizando memória e melhorando o desempenho em cenários com muitos itens iguais.

## Estrutura do Projeto

```
com/sacola/
├── app/
│   └── Main.java                 # Classe principal de demonstração
├── facade/
│   └── PaymentFacade.java        # Implementação do padrão Facade
├── flyweight/
│   ├── Product.java              # Classe do produto (Flyweight)
│   └── ProductFactory.java       # Factory do padrão Flyweight
├── model/
│   ├── CartItem.java             # Item da sacola
│   └── ShoppingBag.java          # Sacola de compras
└── payment/
    ├── BoletoPayment.java        # Pagamento via boleto
    ├── CardPayment.java          # Pagamento via cartão
    ├── CashPayment.java          # Pagamento em espécie
    ├── PaymentInfo.java          # Informações do pagamento
    ├── PaymentMethod.java        # Interface dos métodos de pagamento
    ├── PaymentResult.java        # Resultado do pagamento
    ├── PaymentType.java          # Enum dos tipos de pagamento
    └── PixPayment.java           # Pagamento via Pix
```

## Como funciona

1. Os produtos são criados usando o `ProductFactory`, garantindo reuso de instâncias.
2. Os itens são adicionados à sacola (`ShoppingBag`) como `CartItem`.
3. O pagamento é processado via `PaymentFacade`, que escolhe o método de pagamento conforme o tipo selecionado.
4. O resultado do pagamento é retornado e exibido ao usuário.

## Exemplos de uso

```java
// Criação de produtos usando o padrão Flyweight
// Produtos com o mesmo código compartilham a mesma instância em memória
var camiseta = ProductFactory.getProduct("SKU-100", "Camiseta", new BigDecimal("59.90"));
var calca = ProductFactory.getProduct("SKU-200", "Calça Jeans", new BigDecimal("149.90"));
var meias = ProductFactory.getProduct("SKU-300", "Meias (par)", new BigDecimal("9.90"));

// Montagem da sacola de compras
ShoppingBag sacola = new ShoppingBag();
sacola.addItem(camiseta, 2);  // 2 camisetas
sacola.addItem(calca, 1);     // 1 calça
sacola.addItem(meias, 5);     // 5 pares de meias

System.out.println(sacola);    // Exibe o conteúdo da sacola

// Usando o padrão Facade para processamento de pagamentos
PaymentFacade facade = new PaymentFacade();

// EXEMPLO 1: Pagamento com cartão
var dadosCartao = new HashMap<String,String>();
dadosCartao.put("cardNumber", "1234567890123456");
dadosCartao.put("holder", "FULANO");
dadosCartao.put("cvv", "123");
dadosCartao.put("expiry", "12/25");
PaymentResult resultadoCartao = facade.pay(PaymentType.CARTAO, sacola.getTotal(), dadosCartao);
// Output: success=true, msg=Pagamento por cartão autorizado, tx=CARD-[uuid]

// EXEMPLO 2: Pagamento com boleto
PaymentResult resultadoBoleto = facade.pay(PaymentType.BOLETO, sacola.getTotal(), new HashMap<>());
// Output: success=true, msg=Boleto gerado. Pagar até a data de vencimento, tx=BOLETO-[uuid]

// EXEMPLO 3: Pagamento via Pix
PaymentResult resultadoPix = facade.pay(PaymentType.PIX, sacola.getTotal(), new HashMap<>());
// Output: success=true, msg=Pix realizado com sucesso (simulado), tx=PIX-[uuid]

// EXEMPLO 4: Pagamento em espécie (dinheiro entregue: R$ 500)
var dadosEspecie = new HashMap<String,String>();
dadosEspecie.put("received", "500");
PaymentResult resultadoEspecie = facade.pay(PaymentType.ESPECIE, sacola.getTotal(), dadosEspecie);
// Output: success=true, msg=Pagamento em espécie aceito. Troco: R$ [valor], tx=CASH-OK
```

## Benefícios dos padrões
- **Facade:** Simplifica o uso do sistema e facilita manutenção/extensão.
- **Flyweight:** Reduz o consumo de memória ao reutilizar instâncias de produtos.

## Requisitos
- Java 8 ou superior

## Autores
Projeto acadêmico para a disciplina de Padrões de Projeto com Java feito por William Soares e Richardson Nogueira.