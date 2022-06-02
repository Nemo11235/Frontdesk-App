# Frontdesk-App
This is an application developed using Java by me and my partner.
Since this app is made for my colleagues and manager, and most of them only Chinese, the text in this app is mostly Chinese. But I will be explaining what each part does.
The app can keep track of money transactions, membership, and monthly-pass cards and it will automatically delete the files that are no longer needed. 
The app will start from the login screen, which allows the cashier to enter the infromation for their shift:
<img width="797" alt="initial" src="https://user-images.githubusercontent.com/26106407/171621193-a979cc12-6061-4c03-b0dd-e68280fef6db.png">
And then, they will be at the menu page:
<img width="700" alt="menu" src="https://user-images.githubusercontent.com/26106407/171621281-92dd60f2-9c56-4ee0-85de-7612c4dd8c74.png">
By entering the corresponding number for the item in the input box, the app will ask you how many of this item did the customer purchased. For example:
<img width="601" alt="item" src="https://user-images.githubusercontent.com/26106407/171621704-efa06711-65c6-4523-bcc0-a4c5077c844a.png">
It says that the item you picked is Gatorade, and the unit price is $2.5, the input box below is asking for the amount.
Then the app will bring you to select payment type page. Since the manager wants us to record the payment method of each transection, I implemented this window which contains 3 clickable image buttons to allow cashier to choose the payment method:
<img width="601" alt="payment" src="https://user-images.githubusercontent.com/26106407/171621986-7fc47782-40ea-47b5-827d-3d25d87ccd4f.png">
After that, the app will ask if there's any additional comment:
<img width="600" alt="comment" src="https://user-images.githubusercontent.com/26106407/171622117-bf398f90-b4b4-4c4e-9fb6-f54fc17d6d55.png">
And the result of this line of record will pop out and tells the cashier this transection has been recorded:
<img width="599" alt="showResult" src="https://user-images.githubusercontent.com/26106407/171622220-124b7c5f-9989-4e73-acee-ce825638abc7.png">
The club also sells 10-times pass to the customer. It's basically a plastic card which proves that you pre-paid the entrence fee for 10 times. Each pass has a unique number. Therefore, I also implemented this part where it records the pass number, the guess name, and the customer's phone number:
<img width="599" alt="buyPass" src="https://user-images.githubusercontent.com/26106407/171622756-d9660be0-aa65-4149-8711-b8574a2ea40b.png">
The information above will be saved in a file. From the menu, we can also check if this pass is expired by just enter the pass number:
<img width="693" alt="searchPass" src="https://user-images.githubusercontent.com/26106407/171622987-083c6b53-1008-4136-85f0-eb8e1adf3d51.png">
And here is the result:
<img width="695" alt="resultOfSearchingPass" src="https://user-images.githubusercontent.com/26106407/171623054-636f0dfe-412d-4153-84bd-57eda962b93f.png">
This is the file that stores the information of all the passes. The expired pass will be automatically removed from the file:
<img width="789" alt="passRecords" src="https://user-images.githubusercontent.com/26106407/171623579-64762df3-ab3a-4634-bf11-43fba88602dc.png">
We also need to record the private lesson from each coach, therefore we have this window, which records the name of coach, name of student, begnning and ending time, price and the type of lesson:
 <img width="601" alt="privateLesson" src="https://user-images.githubusercontent.com/26106407/171623221-5f1aaa21-9bcb-409e-bb73-416c80a185b6.png">
And the private lesson of each coach will be recorded in a separate file. For example, this is the file we just recorded for coach Liu:
<img width="676" alt="coachPLRecord" src="https://user-images.githubusercontent.com/26106407/171623267-70569b2c-7c3e-498a-a466-35d9b93219a7.png">
When the club closes, the cashier clicks on the quit button on the menu, the app will calculate and summerize the result of his/her shift and close. Here is the final record of that day:
<img width="803" alt="finalResult" src="https://user-images.githubusercontent.com/26106407/171623420-11adc21f-4fcf-4eb5-8e70-8a07b9d2eee3.png">
