# checkoutKata

1- Clone this project
git clone https://github.com/rpopescu92/checkoutKata.git

2- Build
mvn clean install

3- Run
mvn clean spring-boot:run

4- Postman
localhost:8080/items

Body example:
{
	"products":[
			{"item": "A",
				"price":3
			},
			{"item": "B",
				"price":2
			}
		        ],
	"specialPrices":[
				{
					"item":"A",
					"quantity":2,
					"discountPrice":4
				}
			        ]
}