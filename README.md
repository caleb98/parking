# Parking Program

This program was a project developed for CS 435 - Software Engineering at SIU, Fall 2019. The goal of the project was to create a program for a parking lot that automates customer entry, exit, and payment, and document each step of the development. Below is the final report submitted for the project, which includes this documentation as well as a description of the development process. 

Please excuse any formatting issues - the original report was created in Google Docs so some issues arose when exporting here.

<tbody>

<tr class="c9">

<td class="c31" colspan="1" rowspan="1">

<span class="c35"></span>

<span class="c35"></span>

</td>

<td class="c40" colspan="1" rowspan="1">

<span class="c35"></span>

<span class="c35"></span>

<span class="c35">Team: Team</span>

<span class="c14"></span>

<span class="c35">Members: Alec Waichunas, Caleb Cassady, Donna St. Amour, Frankie Fanelli</span>

<span class="c46"></span>

<span class="c35">Date: December 3rd, 2019</span>

</td>

</tr>

</tbody>

</table>

<span class="c46"></span>

* * *

<span class="c41"></span>

<span class="c50">Table of Contents</span>

<span class="c6"></span>

<span class="c8">[1\. Abstract](#h.4952tybr2exb)</span><span class="c8">        </span><span class="c8">[2](#h.4952tybr2exb)</span>

<span class="c8">[2\. Introduction](#h.bv5fwdabiz79)</span><span class="c8">        </span><span class="c8">[2](#h.bv5fwdabiz79)</span>

<span class="c13">[2.1 Background](#h.awh0qm3c9ma6)</span><span class="c13">        </span><span class="c13">[2](#h.awh0qm3c9ma6)</span>

<span class="c13">[2.2 The Project](#h.2ggz21r1dlie)</span><span class="c13">        </span><span class="c13">[2](#h.2ggz21r1dlie)</span>

<span class="c13">[2.3 The Development](#h.2aehq0h98oqn)</span><span class="c13">        </span><span class="c13">[2](#h.2aehq0h98oqn)</span>

<span class="c8">[3\. Project Requirements](#h.4siq8pphdlqk)</span><span class="c8">        </span><span class="c8">[3](#h.4siq8pphdlqk)</span>

<span class="c13">[3.1 Functional Requirements](#h.sia6brodbwbt)</span><span class="c13">        </span><span class="c13">[3](#h.sia6brodbwbt)</span>

<span class="c13">[3.2 Non-Functional Requirements](#h.pomu9rb635aj)</span><span class="c13">        </span><span class="c13">[5](#h.pomu9rb635aj)</span>

<span class="c6">[3.3 Use Case](#h.q1vl0jurbpdh)</span><span class="c6">        </span><span class="c6">[7](#h.q1vl0jurbpdh)</span>

<span class="c6">[3.4 Class Diagram](#h.ykajntuyj1ka)</span><span class="c6">        </span><span class="c6">[8](#h.ykajntuyj1ka)</span>

<span class="c6">[3.5 Use Sequences](#h.hbe8c3h6igka)</span><span class="c6">        </span><span class="c6">[9](#h.hbe8c3h6igka)</span>

<span class="c6">[3.6 Parking Plus Architecture](#h.uoyptqetucpa)</span><span class="c6">        </span><span class="c6">[12](#h.uoyptqetucpa)</span>

<span class="c1">[4\. Project Implementation](#h.tfaeg4lnisz9)</span><span class="c1">        </span><span class="c1">[13](#h.tfaeg4lnisz9)</span>

<span class="c6">[4.1 Sprint 1](#h.mor0asivr200)</span><span class="c6">        </span><span class="c6">[13](#h.mor0asivr200)</span>

<span class="c6">[4.2 Sprint 2](#h.v1koevptzszd)</span><span class="c6">        </span><span class="c6">[13](#h.v1koevptzszd)</span>

<span class="c6">[4.3 Sprint 3](#h.si25e3k5jv5g)</span><span class="c6">        </span><span class="c6">[14](#h.si25e3k5jv5g)</span>

<span class="c6">[4.4 Test Cases](#h.nm5d3pnsvz7p)</span><span class="c6">        </span><span class="c6">[14](#h.nm5d3pnsvz7p)</span>

<span class="c1">[5\. Conclusion and Future Enhancements](#h.xkv9dxh1wiig)</span><span class="c1">        </span><span class="c1">[14](#h.xkv9dxh1wiig)</span>

<span class="c1">[6\. References](#h.ol6nh9kr2x8t)</span><span class="c1">        </span><span class="c1">[14](#h.ol6nh9kr2x8t)</span>

<span class="c8">[7\. Appendix](#h.j8lkuis8bae9)</span><span class="c8">        </span><span class="c8">[15](#h.j8lkuis8bae9)</span>

<span class="c6"></span>

* * *

# <span class="c30"></span>

# <span class="c30">1\. Abstract</span>

<span>        </span><span class="c36">The goal of this project is to develop a parking application that could be used as a management system for paid parking lots. The application needs to allow for the management of multiple parking lots and sections within the parking lots, as well as manage transactions as customers enter and exit the lot. During development, we aim to carefully design such a program using thorough and concise documentation within the agile design framework.</span>

# <span class="c30">2\. Introduction</span>

## <span class="c15">2.1 Background</span>

<span>        </span><span class="c6">Parking lots can be a crowded and unruly place, especially if there is no system to manage incoming and outgoing cars. Some parking lot owners would like to even charge cars that come through their lot, and can be unnecessary to have a person manage it. A program to create an easy and fast system to assign and pay for parking could solve this problem.</span>

## <span class="c15">2.2 The Project</span>

<span class="c36">We would like to produce an application capable of assigning and paying for a parking spot with ease. As some parking lots are hectic, and dealing with meters can be troublesome and can go unpaid for, a user will pull up to a payment station before entering the parking lot and reserve a spot. The payment will then be processed when they leave the parking lot. The owner of a parking lot can setup the system with ease, adding and removing parking sections to their liking. A transaction log will also be available for the parking lot owner to view who was processed by the program, and how much money they have acquired.</span>

## <span class="c15">2.3 The Development</span>

<span class="c6">        In this project using the Agile software development cycle is most beneficial for the team. Starting with weekly meetups we can determine the necessary objectives to get done and dividend the work up. Next the plan for the project is executed with the design and development of the project. Testing and evaluating the project can follow, and report and merge all changes that took place at the following meeting.</span>

# <span class="c30">3\. Project Requirements</span>

## <span class="c15">3.1 Functional Requirements</span>

<span>        </span><span class="c6">Functional requirements for the program are split into two categories: A.) requirements for customers and; B.) requirements for lot owners. All functional requirement IDs begin with FR, followed by A or B for the aforementioned categories, followed lastly by an indentifying number. The functional requirements are detailed here.</span>

<span class="c6"></span>

<a id="t.10149a78340e37f189eec7008424fbb1c43284fe"></a><a id="t.1"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">FRA1\. Check-in system</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Goal: Save the time, credit card, and spot # when a vehicle has entered the lot.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">When a car enters the lot, we need to take in credit card and license plate numbers and issue a ticket. This system will save the time that they park so that when they leave the lot they get charged for the correct amount of time that they stayed in the lot. The issued ticket will identify which spot the customer should park in.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 3</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.8255fc2b4aef6e814ab12ec6f737d61514e3f338"></a><a id="t.2"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">FRA2\. Check-out system</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Goal: Charge the car-owner's credit card for the time in lot and mark the spot they were in as open.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">When the car leaves the lot, capture the ticket number again and see the time that they've been parked in the lot. Charge the card on file for that license plate and open the gate.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 6</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.8c86142d8e92a92924dc9324ae7df6a5a7f643dc"></a><a id="t.3"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">FRA3\. Payment System</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Goal: Allow for payment with credit/debit cards</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">When a car is entering the parking zone, they will have the ability to pay for their parking ticket using a credit or debit card. Their payment options will be for a standard (pay-per-hour) pass or for a monthly pass at a fixed rate.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 9</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.2f71a007c8c111a4627390a91ef88db76e656562"></a><a id="t.4"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">FRB1\. Transaction log</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Goal: Keep a log of all transactions made by customers parking in the lot.</span>

</td>

</tr>

<tr class="c49">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">All transactions made for parking in the lot should be logged to a secure file. The data about a transaction should include the time the transaction was made, the payment method, the spot the customer was issued, the car's license plate number, and the duration of the park.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 5</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.1d29c0995b4e37762a7c49a8e83632068500d6f1"></a><a id="t.5"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">FRB2\. Set transaction prices</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Goal: Allow lot owners to easily change the price to park/purchase a pass.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Parking lot owners need the ability to set the price that they charge for people to park in the lot. This should at least include an option for the hourly parking rate as well as the flat charge price for the monthly parking pass.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 8</span>

</td>

</tr>

</tbody>

</table>

<span class="c26"></span>

<a id="t.8937f50386f6e9eb4b1920c8d09abcb5836b4553"></a><a id="t.6"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">FRB3\. Saving and Loading Lot Info</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Goal: Lot information should be saved to a file.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">When the parking lot owner edits parking lot/lot section information, the new information should be saved to a file. The information should also be saved if the user exits the program unexpectedly. Whenever the program runs, it should check for the previously saved data and load it.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Sprint 2 Feedback</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 11-21-19</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 3</span>

</td>

</tr>

</tbody>

</table>

## <span class="c15">3.2 Non-Functional Requirements</span>

<span>        </span><span class="c6">Non-functional requirements are not split into categories, but follow a similar ID system using the NFR (non-functional requirement) label followed by an identifying number. The non-functional requirements are detailed here.</span>

<span class="c2"></span>

<a id="t.4c67d0899aaf91b06e0bf4c7361a97cdefe526af"></a><a id="t.7"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">NFR1\. Transaction Data Visibility</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Description: Transaction data should not be visible to customers, but rather only to those authorized to view the data (generally the lot owners.)</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Metric: How secure transaction data is.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 4</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.306b6c9e6b6d3f72d19acdd2177a39347cd0bf38"></a><a id="t.8"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">NFR2\. Credit Card Data</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Description: Credit card data should only be stored for the duration of the customer's park and should be discarded after the transaction has been successfully completed upon their leaving of the lot.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Metric: Whether or not the data is deleted when no longer needed</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 10</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.18c90250ecd5427e9994076b61a9943c3bdcd606"></a><a id="t.9"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">NFR3\. System Speed/Efficiency</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Description: System must be easy enough to process the user efficiently</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Metric: The speed of processing the user through the system</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 6</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.ac6faf0f4b3203a6dc7a9fbdac8d14de0bc5e418"></a><a id="t.10"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">NFR4\. Data Size</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Description: The system needs to be able to hold all data of a parking lot</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Metric: more storage space will have to be allocated</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 8</span>

</td>

</tr>

</tbody>

</table>

<span class="c2"></span>

<a id="t.75937b4ad7cc0268ac51d801e7368c2ff8f7c01d"></a><a id="t.11"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c26">NFR5\. Uptime</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Description: Down time status notification</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c2">Metric: 100% system up time is required to keep it up and running year around</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16">Origin:</span><span class="c2"> Initial brainstorming</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Version:</span><span class="c2"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Date:</span><span class="c2"> 9-26-18</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16">Priority:</span><span class="c2"> 2</span>

</td>

</tr>

</tbody>

</table>

<span class="c34 c24"></span>

<a id="t.3bc584aed3a25ff4c47da85f86410c8e837770d7"></a><a id="t.12"></a>

<table class="c11">

<tbody>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c24 c34">NFR6\. Card Validity</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c18">Description: Only valid cards should be accepted and used for payment.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c18">Metric: Whether or not invalid cards are allowed.</span>

</td>

</tr>

<tr class="c19">

<td class="c22" colspan="3" rowspan="1">

<span class="c16 c24">Origin:</span><span class="c18">  Team Meeting</span>

</td>

</tr>

<tr class="c9">

<td class="c5" colspan="1" rowspan="1">

<span class="c16 c24">Version:</span><span class="c18"> 1.0</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16 c24">Date:</span><span class="c18"> 11-21-19</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c16 c24">Priority:</span><span class="c18"> 4</span>

</td>

</tr>

</tbody>

</table>

* * *

## <span class="c15"></span>

## <span class="c15">3.3 Use Case</span>

<span>        </span><span class="c6">The project has a few actors that interacts with the system. The following use case diagram, shows how an actor should work alongside with the program.</span>

<span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 648.50px; height: 606.00px;">![](images/image4.png)</span>

<span class="c6"></span>

* * *

## <span class="c15"></span>

## <span class="c15">3.4 Class Diagram</span>

<span class="c6">        The program will require a system of objects to communicate with each other, and be able to fulfill the requirements. The diagram below is what we engineered to accomplish this feat.</span><span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 716.95px; height: 465.50px;">![](images/image1.png)</span>

* * *

## <span class="c15"></span>

## <span class="c15">3.5 Use Sequences</span>

<span class="c6">        The use sequences visualize how the actors act on the system and how the system operates in the background. Below are the use cases for when the customers enters or leaves and when the parking lot owner operates the machine.</span>

<span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 421.50px; height: 661.38px;">![](images/image3.png)</span>

<span class="c6">Customer Entering the Lot</span>

<span class="c6">Customer Exiting the Lot</span><span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 647.00px; height: 810.50px;">![](images/image5.png)</span>

<span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 526.50px; height: 743.07px;">![](images/image6.png)</span>

<span class="c6">Owner Operating the System</span>

<span class="c6"></span>

<span class="c6"></span>

## <span class="c15">3.6 Parking Plus Architecture</span>

<span class="c6">        The parking plus architecture is a client-server model. Although it is being simulated using an XML file, this program acts as multiple clients (terminals present at lot entrances) and a server (the ticketmanager loading data on program start and processing transactions in a centralized location).</span>

<span class="c6">The diagram below shows these concepts in a more detailed, visual manner:</span>

<span class="c6"></span>

<span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 624.00px; height: 641.33px;">![](images/image2.png)</span>

# <span class="c30">4\. Project Implementation</span>

## <span class="c15">4.1 Sprint 1</span>

<span>        </span><span class="c36">For the first sprint, we completed our initial project brainstorming and developed the first version of backend code. We decided to develop the program in Java as we wanted the resulting product to be deployable</span> <span>to any parking lot system, and the specific hardware used at every parking lot may differ greatly. This w</span><span class="c6">as also beneficial for our team, which was comprised of developers on MacOS, Windows, and Linux.</span>

<span class="c36">The initial brainstorming for the project included creating UML class diagrams for core components, use case diagrams, and documenting program requirements. We decided that the ideal implementation of our software</span> <span>should be</span> <span class="c36">for parking lots where there are gated entry/exit location(s), each with a terminal that allowed for checking in/out of the lot</span><span class="c6">. In addition, the software would need to manage the number of available spots in each lot, calculate and complete transactions, and validate payment info (potentially with a bank or other reliable entity.)</span>

<span class="c6">        With these details in mind, we programmed the first version of core program classes used to model parking lots and their sections. We also included a simple interface that could be used for terminal hardware such as the gate, card scanner, ticket/receipt printer, etc. During this sprint, we did not aim to produce any code for the functionality of the software; however, we did include within the main method a small amount of test code that ensured proper relationships between the classes we had created.</span>

## <span class="c15">4.2 Sprint 2</span>

<span class="c6">        For the second sprint, our goal was to add the core functionality of the program on top of the backend that we had already developed. We began with a terminal-based check in/out system that read card info from the command line. This allowed us to begin testing card validation as well as the transaction system. While working towards the second sprint, we also created JUnit test classes to confirm the functionality of core system components. For information on these test cases, see section 4.4 below.</span>

<span class="c6">        The next step was to create a user interface that allowed for the management of parking lot. We decided that this interface should also include a pseudo-terminal that mimicked the display that customers would see upon entering/exiting the lot. This user interface was created by using a 3-tab panel containing tabs for editing/managing the parking lots, entering/exiting the lot, and for viewing lot transaction data. Most UI functionality was completed before the second sprint, though data was not persistent (any edits/transactions would be lost upon exiting the program.)</span>

## <span class="c15">4.3 Sprint 3</span>

<span class="c6">        For the third and final sprint, we focused on adding remaining functionality and improving on feedback we received when presenting our progress for sprint 2\. This included data persistence (the ability to save and load edits to the parking lots; the ability to save/export transaction data.) We also removed the remaining command-line interaction by moving the card information entry and ticket ID entry to a pop-up window that appears whenever the "Enter Lot" and "Exit Lot" buttons are pressed on the pseudo customer terminal.</span>

<span class="c6">        We used this sprint as an opportunity to refine our design documents and add two new requirements (FRB3 and NFR6.) We also added model of the architecture our program uses.</span>

## <span class="c15">4.4 Test Cases</span>

<span class="c6">The core functionality of this program required that multiple core functionalities worked correctly. These functionalities include: credit card validation and processing, customers being able to enter and exit a lot, admins being able to create and edit parking lots and sections, tickets printing out with correct information, and transactions processing correctly for administrator bookkeeping. Using JUnit test cases we wrote individual tests for all of these functionalities so that the core functionalities were verified. These tests are all included in the GitHub repository (linked to at the bottom of the file).</span>

# <span class="c30">5\. Conclusion and Future Enhancements</span>

<span class="c6">        Through this project we were able to learn a lot about the requirements that will be required in most tech jobs in the future. This project saw the whole development lifecycle (besides real world deployment, but that’s not quite feasible) and through this we were able to experience what we’ll probably be doing upon employment.</span>

<span class="c6">        Future enhancements that could be made include:</span>

*   <span class="c6">Updating the information storage to use a central database rather than local XML files</span>
*   <span class="c6">Updating the UI to fit modern design principles</span>
*   <span class="c6">Enhancing the security so that customers can’t access the admin terminal</span>

# <span class="c30">6\. References</span>

<span class="c6">Java 8 documentation:</span>

<span class="c43">[https://docs.oracle.com/javase/8/docs/](https://www.google.com/url?q=https://docs.oracle.com/javase/8/docs/&sa=D&ust=1575845601791000)</span>

<span class="c6">Javax swing API documentation:</span>

<span class="c43">[https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html](https://www.google.com/url?q=https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html&sa=D&ust=1575845601792000)</span>

<span class="c6">Mentor’s personal website:</span>

<span>        </span><span class="c43">[https://www2.cs.siu.edu/~kahmed/](https://www.google.com/url?q=https://www2.cs.siu.edu/~kahmed/&sa=D&ust=1575845601793000)</span>

# <span class="c30">7\. Appendix</span>

<span class="c6">Github repository:</span>

<span class="c43">[https://github.com/psychotix/parking](https://www.google.com/url?q=https://github.com/psychotix/parking&sa=D&ust=1575845601794000)</span>

<span class="c6"></span>

<div>

<span class="c6"></span>

</div>
