Monday 11 September

Inheritance is not the key to everything.
Your problem should really be written in stone if you want to use it.
You should be certain that the Objects aren't objects that might change by business requirements.

So instead of Werknemer -> TijdelijkeWerknemer and VasteWerknemer, just use Composition and
create a Contract object for each Werknemer. Why? Because it will be easier to modify in the
future.
