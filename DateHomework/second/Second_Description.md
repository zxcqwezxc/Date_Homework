В одной системе бронирования отелей вам надо написать один небольшой модуль.  
Вам дан массив дат бронирования номера в отеле. Элемент массива или одна дата, или период - две даты через дефис.  
Пример:  
['12.09.2020', '14.09.2020-02.10.2020']  
Вам надо выяснить можно ли добавить в массив новую введенную дату или период для нового бронирования. Например, для указанного выше примера период '01.10.2020-05.10.2020' добавлять нельзя, так как первые два дня уже забронированы.  
В первой строке входных данных содержится информация о дате-времени существующих бронирований, во второй - новая бронь.  
Выведите True, если бронь можно добавить, False - если нельзя.  
Пример 1:  
Ввод:  
['12.09.2020', '14.09.2020-02.10.2020']  
01.10.2020-05.10.2020  
Вывод:  
False  
Пример 2:  
Ввод:  
['14.09.2020-02.10.2020']  
31.12.2020  
Вывод:  
True  