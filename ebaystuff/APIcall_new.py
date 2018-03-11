#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Mar  3 14:47:29 2018

@author: vivekmishra
"""


#Create fulfilment policy

token = "v^1.1#i^1#f^0#I^3#r^0#p^3#t^H4sIAAAAAAAAAOVYeWwUVRjvtlvaWioY2gpidB2OaOvsvjl2dndCV7ct0EXaLmw55CjM8aYdOzuzzptpu4ZgW4VEaUIiDR6JBqImSIIGMCLRavAfiEa8IxgieCsChpiAEjG+2V7bGqEHfzRx/9m8733X7/t+35t5AzqmFJRtrdl6uciVl72rA3Rku1xUISiYklt+c072bblZIEPBtatjboe7K+fnBUhIaEl+OURJQ0fQ057QdMSnhRWEbeq8ISAV8bqQgIi3JD4eqV3K017AJ03DMiRDIzzR6gpCEmQG0rSgBJWQyAX8WKoP+GwwKggRcJwgAYqSJU4OAoD3EbJhVEeWoFsVBA2oIAkYErANVIAHAZ7lvH4QXEN4VkITqYaOVbyACKfT5dO2Zkau105VQAiaFnZChKORRfH6SLR6YV3DAl+Gr3B/HeKWYNlo+KrKkKFnpaDZ8NphUFqbj9uSBBEifOG+CMOd8pGBZMaRfrrUIVEJCooi0lyIAoAL3JBSLjLMhGBdOw9HosqkklbloW6pVup6FcXVEB+CktW/qsMuotUe52+ZLWiqokKzglhYGXlwRXzhcsITj8VMo1WVoewgpRgmxNB0iCXCFkS4hNDc0JpQUbMpALY/WJ/H/lKPiFZl6LLqFA556gyrEuLM4fD6cLw/oz5YqV6vNyOK5WSVqRcarCO1xmlsXydtq1l3egsTuBie9PL6XRigxRARbhQxJFahKFZh6RAtBKHMDCOGM+vjJEfY6U8kFvM5uUBRSJEJwWyBVlITJEhKuLx2ApqqzDN+hWaCCiRlLqSQbEhRSNEvcySlQAggFEUpFPy/ccSyTFW0LTjIk5EbaaAVRFwykjBmaKqUIkaqpM+efla0owqi2bKSvM/X1tbmbWO8htnkowGgfKtrl8alZpgQiEFd9frKpJrmhwSxFVJ5K5XE2bRj+uHgehMRZkw5JphWqtJO4XUcahr+G6DwsAzDI6X/ARU5UCcXSMceYQdCUvU6DPdKRsJnCHiiHdGGdMae0Sj5RDuF48vQ9JpQkA1dS43ersnGDO6zHp0Rwt3w9g0jhjEY0Zn18TgYg42qt2IuG2ZqjDCHG4/BRpAkw9at8YTrNx2DhWJriqppzriOJ2CG+VjS1AUtZakSGk/IjBMZlxepTc3WkJ8JTWskmYzKk2taV6qtsKVWRaQD3cZSMl65mqRlhQsA/P5JygFJFtmAf0K4ZdiqSnCDOsmw67amZSJwZn3M2Kph62TrKeQCLBWiGVLiFIlkRZomBYh7ydIcQwtQ5qgQO6F+1jZNtlbW+SITQlSlqfiEaUhNtodpjYEsKE8MGn6vnVygnBNm4IDxB1mGZAHwk6yssKQYDIkkEwiOGvIIQcar4b9uBr7M67k7B8FwVvpHdbl6QZfrML7jgwAgqXJwz5ScFe6cqQRSLehFgi6LRrtXFRQvfhjo+P5pQm8LTCUF1cye4lo7++x9f2V8Gti1Hswc/DhQkEMVZnwpALcP7eRS024tooKAASwVAAGWWwPmDO26qVJ3cXf+rx/Pj73/hXvVjs2Ly3/f+Nbx0z2gaFDJ5crNcne5surZ39btNPY+Gu2Mzi6b/sGHf7zAnYoU14VLftmx/1xe94Vm7uwMV+fnmw5s7G08dXDal/lPbf804Qo+vC9/Ltr22jc71+X9+e3b/BU4f+6FLVcvF27reazt3YItkvVkz01bzl964zH6kZca1+avKtn/91eLP3tnb6H3rhOlM87t/uRgrLGXOnJH2dGPjl3qnlO6flrR5mft4ovv7dv443Obl+yxZs3S3PevjUx//vDMbdHz0svFV54p6X3ih84zy0vvznvgwumqr/mTVxsKT7yeOvn4psCb8Du4pKamdF7X5TtfPXP0p9WHLjYW3WKUlcyrfrH7eHL2gSMHtz/9fXlRz72dh15ZcXV3y7Ks5NQ9x/KPz+9r4z+atXVotBEAAA=="
headers = {'Authorization': 'Bearer '+token,
           'Content-Type':'application/json',
           'X-EBAY-C-MARKETPLACE-ID':'EBAY_US'}

url = 'https://api.sandbox.ebay.com/sell/account/v1/fulfillment_policy?marketplace_id=EBAY_US'
r = requests.get(url, headers=headers)
returns = r.json()

#Payment policy
token = "v^1.1#i^1#f^0#I^3#r^0#p^3#t^H4sIAAAAAAAAAOVYeWwUVRjvtlvaWioY2gpidB2OaOvsvjl2dndCV7ct0EXaLmw55CjM8aYdOzuzzptpu4ZgW4VEaUIiDR6JBqImSIIGMCLRavAfiEa8IxgieCsChpiAEjG+2V7bGqEHfzRx/9m8733X7/t+35t5AzqmFJRtrdl6uciVl72rA3Rku1xUISiYklt+c072bblZIEPBtatjboe7K+fnBUhIaEl+OURJQ0fQ057QdMSnhRWEbeq8ISAV8bqQgIi3JD4eqV3K017AJ03DMiRDIzzR6gpCEmQG0rSgBJWQyAX8WKoP+GwwKggRcJwgAYqSJU4OAoD3EbJhVEeWoFsVBA2oIAkYErANVIAHAZ7lvH4QXEN4VkITqYaOVbyACKfT5dO2Zkau105VQAiaFnZChKORRfH6SLR6YV3DAl+Gr3B/HeKWYNlo+KrKkKFnpaDZ8NphUFqbj9uSBBEifOG+CMOd8pGBZMaRfrrUIVEJCooi0lyIAoAL3JBSLjLMhGBdOw9HosqkklbloW6pVup6FcXVEB+CktW/qsMuotUe52+ZLWiqokKzglhYGXlwRXzhcsITj8VMo1WVoewgpRgmxNB0iCXCFkS4hNDc0JpQUbMpALY/WJ/H/lKPiFZl6LLqFA556gyrEuLM4fD6cLw/oz5YqV6vNyOK5WSVqRcarCO1xmlsXydtq1l3egsTuBie9PL6XRigxRARbhQxJFahKFZh6RAtBKHMDCOGM+vjJEfY6U8kFvM5uUBRSJEJwWyBVlITJEhKuLx2ApqqzDN+hWaCCiRlLqSQbEhRSNEvcySlQAggFEUpFPy/ccSyTFW0LTjIk5EbaaAVRFwykjBmaKqUIkaqpM+efla0owqi2bKSvM/X1tbmbWO8htnkowGgfKtrl8alZpgQiEFd9frKpJrmhwSxFVJ5K5XE2bRj+uHgehMRZkw5JphWqtJO4XUcahr+G6DwsAzDI6X/ARU5UCcXSMceYQdCUvU6DPdKRsJnCHiiHdGGdMae0Sj5RDuF48vQ9JpQkA1dS43ersnGDO6zHp0Rwt3w9g0jhjEY0Zn18TgYg42qt2IuG2ZqjDCHG4/BRpAkw9at8YTrNx2DhWJriqppzriOJ2CG+VjS1AUtZakSGk/IjBMZlxepTc3WkJ8JTWskmYzKk2taV6qtsKVWRaQD3cZSMl65mqRlhQsA/P5JygFJFtmAf0K4ZdiqSnCDOsmw67amZSJwZn3M2Kph62TrKeQCLBWiGVLiFIlkRZomBYh7ydIcQwtQ5qgQO6F+1jZNtlbW+SITQlSlqfiEaUhNtodpjYEsKE8MGn6vnVygnBNm4IDxB1mGZAHwk6yssKQYDIkkEwiOGvIIQcar4b9uBr7M67k7B8FwVvpHdbl6QZfrML7jgwAgqXJwz5ScFe6cqQRSLehFgi6LRrtXFRQvfhjo+P5pQm8LTCUF1cye4lo7++x9f2V8Gti1Hswc/DhQkEMVZnwpALcP7eRS024tooKAASwVAAGWWwPmDO26qVJ3cXf+rx/Pj73/hXvVjs2Ly3/f+Nbx0z2gaFDJ5crNcne5surZ39btNPY+Gu2Mzi6b/sGHf7zAnYoU14VLftmx/1xe94Vm7uwMV+fnmw5s7G08dXDal/lPbf804Qo+vC9/Ltr22jc71+X9+e3b/BU4f+6FLVcvF27reazt3YItkvVkz01bzl964zH6kZca1+avKtn/91eLP3tnb6H3rhOlM87t/uRgrLGXOnJH2dGPjl3qnlO6flrR5mft4ovv7dv443Obl+yxZs3S3PevjUx//vDMbdHz0svFV54p6X3ih84zy0vvznvgwumqr/mTVxsKT7yeOvn4psCb8Du4pKamdF7X5TtfPXP0p9WHLjYW3WKUlcyrfrH7eHL2gSMHtz/9fXlRz72dh15ZcXV3y7Ks5NQ9x/KPz+9r4z+atXVotBEAAA=="
headers = {'Authorization': 'Bearer '+token,
           'Content-Type':'application/json',
           'X-EBAY-C-MARKETPLACE-ID':'EBAY_US'}

url = 'https://api.sandbox.ebay.com/sell/account/v1/payment_policy?marketplace_id=EBAY_US'
r = requests.get(url, headers=headers)
returns = r.json()

#Return policy
token = "v^1.1#i^1#f^0#I^3#r^0#p^3#t^H4sIAAAAAAAAAOVYeWwUVRjvtlvaWioY2gpidB2OaOvsvjl2dndCV7ct0EXaLmw55CjM8aYdOzuzzptpu4ZgW4VEaUIiDR6JBqImSIIGMCLRavAfiEa8IxgieCsChpiAEjG+2V7bGqEHfzRx/9m8733X7/t+35t5AzqmFJRtrdl6uciVl72rA3Rku1xUISiYklt+c072bblZIEPBtatjboe7K+fnBUhIaEl+OURJQ0fQ057QdMSnhRWEbeq8ISAV8bqQgIi3JD4eqV3K017AJ03DMiRDIzzR6gpCEmQG0rSgBJWQyAX8WKoP+GwwKggRcJwgAYqSJU4OAoD3EbJhVEeWoFsVBA2oIAkYErANVIAHAZ7lvH4QXEN4VkITqYaOVbyACKfT5dO2Zkau105VQAiaFnZChKORRfH6SLR6YV3DAl+Gr3B/HeKWYNlo+KrKkKFnpaDZ8NphUFqbj9uSBBEifOG+CMOd8pGBZMaRfrrUIVEJCooi0lyIAoAL3JBSLjLMhGBdOw9HosqkklbloW6pVup6FcXVEB+CktW/qsMuotUe52+ZLWiqokKzglhYGXlwRXzhcsITj8VMo1WVoewgpRgmxNB0iCXCFkS4hNDc0JpQUbMpALY/WJ/H/lKPiFZl6LLqFA556gyrEuLM4fD6cLw/oz5YqV6vNyOK5WSVqRcarCO1xmlsXydtq1l3egsTuBie9PL6XRigxRARbhQxJFahKFZh6RAtBKHMDCOGM+vjJEfY6U8kFvM5uUBRSJEJwWyBVlITJEhKuLx2ApqqzDN+hWaCCiRlLqSQbEhRSNEvcySlQAggFEUpFPy/ccSyTFW0LTjIk5EbaaAVRFwykjBmaKqUIkaqpM+efla0owqi2bKSvM/X1tbmbWO8htnkowGgfKtrl8alZpgQiEFd9frKpJrmhwSxFVJ5K5XE2bRj+uHgehMRZkw5JphWqtJO4XUcahr+G6DwsAzDI6X/ARU5UCcXSMceYQdCUvU6DPdKRsJnCHiiHdGGdMae0Sj5RDuF48vQ9JpQkA1dS43ersnGDO6zHp0Rwt3w9g0jhjEY0Zn18TgYg42qt2IuG2ZqjDCHG4/BRpAkw9at8YTrNx2DhWJriqppzriOJ2CG+VjS1AUtZakSGk/IjBMZlxepTc3WkJ8JTWskmYzKk2taV6qtsKVWRaQD3cZSMl65mqRlhQsA/P5JygFJFtmAf0K4ZdiqSnCDOsmw67amZSJwZn3M2Kph62TrKeQCLBWiGVLiFIlkRZomBYh7ydIcQwtQ5qgQO6F+1jZNtlbW+SITQlSlqfiEaUhNtodpjYEsKE8MGn6vnVygnBNm4IDxB1mGZAHwk6yssKQYDIkkEwiOGvIIQcar4b9uBr7M67k7B8FwVvpHdbl6QZfrML7jgwAgqXJwz5ScFe6cqQRSLehFgi6LRrtXFRQvfhjo+P5pQm8LTCUF1cye4lo7++x9f2V8Gti1Hswc/DhQkEMVZnwpALcP7eRS024tooKAASwVAAGWWwPmDO26qVJ3cXf+rx/Pj73/hXvVjs2Ly3/f+Nbx0z2gaFDJ5crNcne5surZ39btNPY+Gu2Mzi6b/sGHf7zAnYoU14VLftmx/1xe94Vm7uwMV+fnmw5s7G08dXDal/lPbf804Qo+vC9/Ltr22jc71+X9+e3b/BU4f+6FLVcvF27reazt3YItkvVkz01bzl964zH6kZca1+avKtn/91eLP3tnb6H3rhOlM87t/uRgrLGXOnJH2dGPjl3qnlO6flrR5mft4ovv7dv443Obl+yxZs3S3PevjUx//vDMbdHz0svFV54p6X3ih84zy0vvznvgwumqr/mTVxsKT7yeOvn4psCb8Du4pKamdF7X5TtfPXP0p9WHLjYW3WKUlcyrfrH7eHL2gSMHtz/9fXlRz72dh15ZcXV3y7Ks5NQ9x/KPz+9r4z+atXVotBEAAA=="
headers = {'Authorization': 'Bearer '+token,
           'Content-Type':'application/json',
           'X-EBAY-C-MARKETPLACE-ID':'EBAY_US'}

url = 'https://api.sandbox.ebay.com/sell/account/v1/return_policy?marketplace_id=EBAY_US'
r = requests.get(url, headers=headers)
returns = r.json()


#Create inventory
token = "v^1.1#i^1#f^0#I^3#r^0#p^3#t^H4sIAAAAAAAAAOVYeWwUVRjvtlvaWioY2gpidB2OaOvsvjl2dndCV7ct0EXaLmw55CjM8aYdOzuzzptpu4ZgW4VEaUIiDR6JBqImSIIGMCLRavAfiEa8IxgieCsChpiAEjG+2V7bGqEHfzRx/9m8733X7/t+35t5AzqmFJRtrdl6uciVl72rA3Rku1xUISiYklt+c072bblZIEPBtatjboe7K+fnBUhIaEl+OURJQ0fQ057QdMSnhRWEbeq8ISAV8bqQgIi3JD4eqV3K017AJ03DMiRDIzzR6gpCEmQG0rSgBJWQyAX8WKoP+GwwKggRcJwgAYqSJU4OAoD3EbJhVEeWoFsVBA2oIAkYErANVIAHAZ7lvH4QXEN4VkITqYaOVbyACKfT5dO2Zkau105VQAiaFnZChKORRfH6SLR6YV3DAl+Gr3B/HeKWYNlo+KrKkKFnpaDZ8NphUFqbj9uSBBEifOG+CMOd8pGBZMaRfrrUIVEJCooi0lyIAoAL3JBSLjLMhGBdOw9HosqkklbloW6pVup6FcXVEB+CktW/qsMuotUe52+ZLWiqokKzglhYGXlwRXzhcsITj8VMo1WVoewgpRgmxNB0iCXCFkS4hNDc0JpQUbMpALY/WJ/H/lKPiFZl6LLqFA556gyrEuLM4fD6cLw/oz5YqV6vNyOK5WSVqRcarCO1xmlsXydtq1l3egsTuBie9PL6XRigxRARbhQxJFahKFZh6RAtBKHMDCOGM+vjJEfY6U8kFvM5uUBRSJEJwWyBVlITJEhKuLx2ApqqzDN+hWaCCiRlLqSQbEhRSNEvcySlQAggFEUpFPy/ccSyTFW0LTjIk5EbaaAVRFwykjBmaKqUIkaqpM+efla0owqi2bKSvM/X1tbmbWO8htnkowGgfKtrl8alZpgQiEFd9frKpJrmhwSxFVJ5K5XE2bRj+uHgehMRZkw5JphWqtJO4XUcahr+G6DwsAzDI6X/ARU5UCcXSMceYQdCUvU6DPdKRsJnCHiiHdGGdMae0Sj5RDuF48vQ9JpQkA1dS43ersnGDO6zHp0Rwt3w9g0jhjEY0Zn18TgYg42qt2IuG2ZqjDCHG4/BRpAkw9at8YTrNx2DhWJriqppzriOJ2CG+VjS1AUtZakSGk/IjBMZlxepTc3WkJ8JTWskmYzKk2taV6qtsKVWRaQD3cZSMl65mqRlhQsA/P5JygFJFtmAf0K4ZdiqSnCDOsmw67amZSJwZn3M2Kph62TrKeQCLBWiGVLiFIlkRZomBYh7ydIcQwtQ5qgQO6F+1jZNtlbW+SITQlSlqfiEaUhNtodpjYEsKE8MGn6vnVygnBNm4IDxB1mGZAHwk6yssKQYDIkkEwiOGvIIQcar4b9uBr7M67k7B8FwVvpHdbl6QZfrML7jgwAgqXJwz5ScFe6cqQRSLehFgi6LRrtXFRQvfhjo+P5pQm8LTCUF1cye4lo7++x9f2V8Gti1Hswc/DhQkEMVZnwpALcP7eRS024tooKAASwVAAGWWwPmDO26qVJ3cXf+rx/Pj73/hXvVjs2Ly3/f+Nbx0z2gaFDJ5crNcne5surZ39btNPY+Gu2Mzi6b/sGHf7zAnYoU14VLftmx/1xe94Vm7uwMV+fnmw5s7G08dXDal/lPbf804Qo+vC9/Ltr22jc71+X9+e3b/BU4f+6FLVcvF27reazt3YItkvVkz01bzl964zH6kZca1+avKtn/91eLP3tnb6H3rhOlM87t/uRgrLGXOnJH2dGPjl3qnlO6flrR5mft4ovv7dv443Obl+yxZs3S3PevjUx//vDMbdHz0svFV54p6X3ih84zy0vvznvgwumqr/mTVxsKT7yeOvn4psCb8Du4pKamdF7X5TtfPXP0p9WHLjYW3WKUlcyrfrH7eHL2gSMHtz/9fXlRz72dh15ZcXV3y7Ks5NQ9x/KPz+9r4z+atXVotBEAAA=="
headers = {'Authorization': 'Bearer '+token,
           'Content-Type':'application/json',
           'X-EBAY-C-MARKETPLACE-ID':'EBAY_US'}

url = 'https://api.sandbox.ebay.com/sell/account/v1/return_policy?marketplace_id=EBAY_US'
body={ 
    "availability": { 
        "shipToLocationAvailability": { 
            "quantity": 50
        }
    },
    "condition": "NEW",
    "product": { 
        "title": "GoPro Hero4 Helmet Cam",
        "description": "New GoPro Hero4 Helmet Cam. Unopened box.",
					"aspects":{
						"Brand" :["GoPro"],
						"Type" : ["Helmet/Action"],
						"Storage Type" : ["Removable"],
						"Recording Definition" : ["High Definition"],
						"Media Format" : ["Flash Drive (SSD)"],
						"Optical Zoom" : ["10x"]
					},
					"brand":"GoPro",
					"mpn":"CHDHX-401",
        "imageUrls": [
            "http://i.ebayimg.com/images/i/182196556219-0-1/s-l1000.jpg",
            "http://i.ebayimg.com/images/i/182196556219-0-1/s-l1001.jpg",
            "http://i.ebayimg.com/images/i/182196556219-0-1/s-l1002.jpg"
        ]
    }
}
r = requests.get(url, headers=headers,data = body)
returns = r.json()

#
token = "v^1.1#i^1#f^0#I^3#r^0#p^3#t^H4sIAAAAAAAAAOVYeWwUVRjvtlvaWioY2gpidB2OaOvsvjl2dndCV7ct0EXaLmw55CjM8aYdOzuzzptpu4ZgW4VEaUIiDR6JBqImSIIGMCLRavAfiEa8IxgieCsChpiAEjG+2V7bGqEHfzRx/9m8733X7/t+35t5AzqmFJRtrdl6uciVl72rA3Rku1xUISiYklt+c072bblZIEPBtatjboe7K+fnBUhIaEl+OURJQ0fQ057QdMSnhRWEbeq8ISAV8bqQgIi3JD4eqV3K017AJ03DMiRDIzzR6gpCEmQG0rSgBJWQyAX8WKoP+GwwKggRcJwgAYqSJU4OAoD3EbJhVEeWoFsVBA2oIAkYErANVIAHAZ7lvH4QXEN4VkITqYaOVbyACKfT5dO2Zkau105VQAiaFnZChKORRfH6SLR6YV3DAl+Gr3B/HeKWYNlo+KrKkKFnpaDZ8NphUFqbj9uSBBEifOG+CMOd8pGBZMaRfrrUIVEJCooi0lyIAoAL3JBSLjLMhGBdOw9HosqkklbloW6pVup6FcXVEB+CktW/qsMuotUe52+ZLWiqokKzglhYGXlwRXzhcsITj8VMo1WVoewgpRgmxNB0iCXCFkS4hNDc0JpQUbMpALY/WJ/H/lKPiFZl6LLqFA556gyrEuLM4fD6cLw/oz5YqV6vNyOK5WSVqRcarCO1xmlsXydtq1l3egsTuBie9PL6XRigxRARbhQxJFahKFZh6RAtBKHMDCOGM+vjJEfY6U8kFvM5uUBRSJEJwWyBVlITJEhKuLx2ApqqzDN+hWaCCiRlLqSQbEhRSNEvcySlQAggFEUpFPy/ccSyTFW0LTjIk5EbaaAVRFwykjBmaKqUIkaqpM+efla0owqi2bKSvM/X1tbmbWO8htnkowGgfKtrl8alZpgQiEFd9frKpJrmhwSxFVJ5K5XE2bRj+uHgehMRZkw5JphWqtJO4XUcahr+G6DwsAzDI6X/ARU5UCcXSMceYQdCUvU6DPdKRsJnCHiiHdGGdMae0Sj5RDuF48vQ9JpQkA1dS43ersnGDO6zHp0Rwt3w9g0jhjEY0Zn18TgYg42qt2IuG2ZqjDCHG4/BRpAkw9at8YTrNx2DhWJriqppzriOJ2CG+VjS1AUtZakSGk/IjBMZlxepTc3WkJ8JTWskmYzKk2taV6qtsKVWRaQD3cZSMl65mqRlhQsA/P5JygFJFtmAf0K4ZdiqSnCDOsmw67amZSJwZn3M2Kph62TrKeQCLBWiGVLiFIlkRZomBYh7ydIcQwtQ5qgQO6F+1jZNtlbW+SITQlSlqfiEaUhNtodpjYEsKE8MGn6vnVygnBNm4IDxB1mGZAHwk6yssKQYDIkkEwiOGvIIQcar4b9uBr7M67k7B8FwVvpHdbl6QZfrML7jgwAgqXJwz5ScFe6cqQRSLehFgi6LRrtXFRQvfhjo+P5pQm8LTCUF1cye4lo7++x9f2V8Gti1Hswc/DhQkEMVZnwpALcP7eRS024tooKAASwVAAGWWwPmDO26qVJ3cXf+rx/Pj73/hXvVjs2Ly3/f+Nbx0z2gaFDJ5crNcne5surZ39btNPY+Gu2Mzi6b/sGHf7zAnYoU14VLftmx/1xe94Vm7uwMV+fnmw5s7G08dXDal/lPbf804Qo+vC9/Ltr22jc71+X9+e3b/BU4f+6FLVcvF27reazt3YItkvVkz01bzl964zH6kZca1+avKtn/91eLP3tnb6H3rhOlM87t/uRgrLGXOnJH2dGPjl3qnlO6flrR5mft4ovv7dv443Obl+yxZs3S3PevjUx//vDMbdHz0svFV54p6X3ih84zy0vvznvgwumqr/mTVxsKT7yeOvn4psCb8Du4pKamdF7X5TtfPXP0p9WHLjYW3WKUlcyrfrH7eHL2gSMHtz/9fXlRz72dh15ZcXV3y7Ks5NQ9x/KPz+9r4z+atXVotBEAAA=="
headers = {'Authorization': 'Bearer '+token,
           'Content-Type':'application/json',
           'X-EBAY-C-MARKETPLACE-ID':'EBAY_US'}

url = 'https://api.sandbox.ebay.com/sell/inventory/v1/offer'

body = {
   "sku": "progo",
   "marketplaceId": "EBAY_US",
   "format": "FIXED_PRICE",
   "availableQuantity": 75,
   "categoryId": "30120",
   "listingDescription": "Lumia phone with a stunning 5.7 inch Quad HD display and a powerful octa-core processor.",
   "listingPolicies": {
      "fulfillmentPolicyId": "5746658000",
      "paymentPolicyId": "5746663000",
      "returnPolicyId": "5746664000"
   },
   "pricingSummary": {
      "price": {
         "currency": "USD",
         "value": "272.17"
      }
   },
   "quantityLimitPerBuyer": 2
}

r = requests.get(url, headers=headers,data = body)
returns = r.json()















#API for checking fulfillment
import requests

token = "v^1.1#i^1#r^0#p^3#f^0#I^3#t^H4sIAAAAAAAAAOVYa2wURRzv0QfylA8IAo0eK0J47N3s414benh9YCv0AVcqYLCd3Z1t1+7tXnZmez0VaWqsmpB+MIqJCdCECiFRggbQmmgIicGkRj5pNKJNUHlJaKIiBo04e31da4Q++NDEyyWbmfm/fr///z87s6CtYNaajvKOm/M8M2d0tYG2GR4PNwfMKshfOz93xtL8HJAl4OlqW9GW1557eT2GCSMpbUU4aZkYeVsThomlzGQR49imZEGsY8mECYQlokjxWOVmifcBKWlbxFIsg/FWlBYxwYgialBFwQCnBmBQpLPmkM1aq4hRIB/UtJAsh1VRUYIBuo6xgypMTKBJihgecGEWCPRfy/OSGJCEsE/kxZ2Mtw7ZWLdMKuIDTDQTrpTRtbNivXOoEGNkE2qEiVbENsarYxWlZVW16/1ZtqKDPMQJJA4ePSqxVOStg4aD7uwGZ6SluKMoCGPGHx3wMNqoFBsKZhLhZ6gWhCCUtRDPC1xI1eC9oXKjZScguXMc7oyuslpGVEIm0Un6boxSNuRnkEIGR1XUREWp131scaChazqyi5iy4tiObfGyrYw3XlNjWy26ilQXKScIEYHnIyITJQhTCpFd35LQcZMNgTjobMDiINVjvJVYpqq7xGFvlUWKEY0cjeVHyOKHClWb1XZMI25Uw3JiLQBDPHKBnW5iBzLpkCbTzS1KUDK8meHdszBUFiOFcK8KQ+VVQRAjgqiFkApCYFRhuL0+yeKIuvmJ1dT43ViQDNNsAtrNiCQNqCBWofQ6CWTrKuVS44Wwhlg1GNFYMaJprBxQgyynIQQQkmUlEv6/1Qghti47BA3XydiFDNAiJq5YSVRjGbqSZsaKZPaewapoxUVMEyFJye9PpVK+lOCz7EY/DwDn3165Oa40oQRkhmX1uwuzeqY+FES1sC6RdJJG00rLjzo3G5moYKs10CbpYidNx3FkGPQxVMKjIoyOnf0PqNiFOr1AuvqYGoBJ3edWuE+xEn4L0o52p+ozEXvHI+SXnTT1ryLbZyOoWqaRHr9eo0MreEB7fEqYZsM30IwUxrBHt9cnY2ACOrrZQmvZstMThDlaeQI6UFEsxySTcTeoOgENzTE03TDcdp2Mwyz1iYRpQiNNdAVPxmXWjkzpxXpjExmxM6VujSWTFer06tY6vQU1V+qYdaE7dJaNF29neVULhkBYi7BqSFFlMRSYEm4VtegKqtenGXbTMYxsBG6vTxhbKWqZbjlFwZDIRXiBVYKawooyz7MQ0VyKfFDgIVKDXEScUj4rG6dbKqv8sSkhKjF0usPUpqfby7TcwgSpU4NGz7XTC5S7wwxtMIGwKLAiAAFWVDWRlcMRmRVC4XFDHjORdTT8183An309z8vFKJqT+XHtno9Bu6eH3vHpSZ/l1oLVBbnb8nLnMlgnyIehqcpWq0+Hmo++DEx6/7SRrxmlk1C3ZxR4nlp2dcNfWZ8GunaBB4c/DszK5eZkfSkAhSMr+dz9i+dxYSAAeuYWA0J4J3hkZDWPW5S3cG3+rY7zxzr/7Ok5fv3wMqA47Qu+AvOGhTye/Jy8dk/Ouu3nj350aPHC+as/7/bIJzrLt30/++be3jr4dBu38tV+4/X79l3L2/N1SWd/36prC+CVrobWlVVnlm9J/lB9+sDvvzrB6m5f/WelWzuiZ9fVSOF3+0nDY82r3j5kLLqReuJ22czaF75b8fDJ566e7bt8Xbt44sI7524t3n1maYM/3p36UFh+9NQf+QeZHvlAz49vXkqlLuz9gn//g30VjD5b7tvRefWnxt/OLdlxo2/J3CuF5/eTb7+8+doD7/38RuhI+OKnp54/hn/xfHN7EdjVveeV3vIqboP38IsHG166Nv90z8bU31qypPehTza9fPxSXvR6b+5+8Oy5xx+Nv7VpzZrC/LLdT8ZOHunPKewZSOM/yT2habQRAAA=" 


headers = {'Authorization': 'Bearer '+token,
           'Content-Type':'application/json',
           'X-EBAY-C-MARKETPLACE-ID':'EBAY_US'}
body = {
    "availability": {
        "shipToLocationAvailability": {
            "quantity": 50
        }
    },
    "condition": "NEW",
    "product": {
        "title": "GoPro Hero4 Helmet Cam",
        "description": "New GoPro Hero4 Helmet Cam. Unopened box.",
        "aspects": {
            "Brand": [
                "GoPro"
            ],
            "Type": [
                "Helmet/Action"
            ],
            "Storage Type": [
                "Removable"
            ],
            "Recording Definition": [
                "High Definition"
            ],
            "Media Format": [
                "Flash Drive (SSD)"
            ],
            "Optical Zoom": [
                "10x"
            ]
        },
        "brand": "GoPro",
        "mpn": "CHDHX-401",
        "imageUrls": [
            "http://i.ebayimg.com/images/i/182196556219-0-1/s-l1000.jpg",
            "http://i.ebayimg.com/images/i/182196556219-0-1/s-l1001.jpg",
            "http://i.ebayimg.com/images/i/182196556219-0-1/s-l1002.jpg"
        ]
    }
}
url = 'https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/apple_watch'
r = requests.get(url, headers=headers,data= body)
returns = r.json()
















https://hacktech2018@sellustrate.scm.azurewebsites.net/sellustrate.git
hacktech2018
sellustrate
https://sellustrate@sellustrate.scm.azurewebsites.net/sellustrate.git
https://sellustrate@sellustrate.scm.azurewebsites.net/sellustrate.git




