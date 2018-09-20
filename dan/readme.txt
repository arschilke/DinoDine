Included values mainly for strings.xml which holds values for person select. May look at doing it dynamically but for now this is fine.

Layout:
	people_select: 	complete structurally.

	booking_view: 	has placeholder textViews for 'time','date','name', etc for bookings
		      	with a scroll view below to hold the data when I get around to loading it in.

	table_select: 	Bit of a hack with tableLayout, invisible buttons, and spaces.
		      	Will have another go using Layouts as they seem a bit more flexible.

java/PeopleSelect.java:	Grabs string-array from strings.xml and displays it in the spinner.