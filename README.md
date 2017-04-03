# Spotippos

Projeto de testes que explora as principais features do Spring, JDK-1.8 e performance de coleções de dados.

## Tecnologias
- Java 8;
- Spring-Boot;
- Spring-Web;
- Gradle;
- Jackson

## Instruções

<pre>
git clone https://github.com/rbarbioni/Spotippos.git

cd Spotippos

Unix

chmod +x gradlew
./gradlew build

Windows
gradlew.bat

java -jar build/libs/Spotippos-0.0.1-SNAPSHOT.war
</pre>

## Consumindo a API



GET --> /properties/1
<pre>
RESPONSE
{
  "x": 1257,
  "y": 928,
  "title": "Imóvel código 1, com 3 quartos e 2 banheiros.",
  "price": 643000,
  "description": "Laboris quis quis elit commodo eiusmod qui exercitation. In laborum fugiat quis minim occaecat id.",
  "beds": 3,
  "baths": 2,
  "squareMeters": 61,
  "provinces": [
    "Jaby"
  ],
  "id": 1
}
</pre>


POST --> /properties
<pre>
REQUEST
{
	"x": 1257,
    "y": 928,
    "description": "description",
    "title": "title",
    "price": 500.52,
    "beds": 1,
    "baths": 1,
    "squareMeters": 21
}
RESPONSE
{
  "x": 1257,
  "y": 928,
  "title": "title",
  "price": 500.52,
  "description": "description",
  "beds": 1,
  "baths": 1,
  "squareMeters": 21,
  "provinces": [
    "Jaby"
  ],
  "id": 8001
}
</pre>

GET --> /properties?ax=0&bx=700&ay=0&by=500
<pre>
RESPONSE
{
  "foundProperties": 2016,
  "properties": [
    {
      "x": 139,
      "y": 202,
      "title": "Imóvel código 7000, com 5 quartos e 4 banheiros.",
      "price": 1842000,
      "description": "Est eiusmod do velit cillum nulla cupidatat proident. Minim mollit sunt dolore voluptate qui sunt.",
      "beds": 5,
      "baths": 4,
      "squareMeters": 184,
      "provinces": [
        "Scavy"
      ],
      "id": 7000
    },
    {
      "x": 185,
      "y": 35,
      "title": "Imóvel código 2200, com 5 quartos e 4 banheiros.",
      "price": 1960000,
      "description": "Nisi dolore duis amet est Lorem irure Lorem proident nulla adipisicing eu. Deserunt dolor proident veniam magna esse officia eu laboris.",
      "beds": 5,
      "baths": 4,
      "squareMeters": 195,
      "provinces": [
        "Scavy"
      ],
      "id": 2200
    },
    {...},
    {...}
  ]
</pre>

## Rodando em Containers ou Servidores de Aplicação Java

Foi utilizado o plugin Gradle "war" e o setup do projeto foi desenvolvido para rodar de forma embedded ou fazendo deploy em containers Java.

Basta copiar arquivo build/libs/Spotippos-0.0.1-SNAPSHOT.war para pasta deployment do servidor de sua preferência.

EX:
Tomcat;
Jboss;
Jety;
etc..


