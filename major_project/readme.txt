Input API: coinmarketcap
Output API: sendgrid
Claimed Tier: Credit
Credit Optional Feature 1: about page
Credit Optional Feature 2: html email
Distinction Optional Feature:
High Distinction Optional Feature:

Milestone 1 Submission:
    SHA: aa5d1863271c2e0d5562237ddf559055e2a9d1f4
    URI: https://github.sydney.edu.au/lpol8511/SCD2_2022/commit/aa5d1863271c2e0d5562237ddf559055e2a9d1f4
Milestone 1 Re-Submission:
    SHA:
    URI:
Milestone 2 Submission:
    SHA: 96a740ba7a8a513864277975e9977001dd1476e9
    URI: https://github.sydney.edu.au/lpol8511/SCD2_2022/commit/96a740ba7a8a513864277975e9977001dd1476e9
Milestone 2 Re-Submission:
    SHA: 238386f131df240a288b2042ed3d521879699bc0
    URI: https://github.sydney.edu.au/lpol8511/SCD2_2022/commit/238386f131df240a288b2042ed3d521879699bc0
Exam Base Commit:
    SHA: 238386f131df240a288b2042ed3d521879699bc0
    URI: https://github.sydney.edu.au/lpol8511/SCD2_2022/commit/238386f131df240a288b2042ed3d521879699bc0
Exam Submission Commit:
    SHA: 3642f8e55322b786b78a8685fae8c3188927462f
    URI: https://github.sydney.edu.au/lpol8511/SCD2_2022/commit/3642f8e55322b786b78a8685fae8c3188927462f

run:
gradle run --args="online online"
gradle run --args="offline offline"

** --args="inputAPI outputAPI" **
=======
** --args="inputAPI outputAPI" **

refs:
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html

notes:
about page(click details then about)
html email(designed looking at https://mail.google.com/ inbox)

exam feature:
At the beginning of the application, ask the user for a balance. Every time they do a conversion,
the amount entered 'from' (i.e. convert '10' of this currency) should be subtracted from this balance.
When the balance would go below 0, the conversion should be rejected with an alert saying 'Out of
money'. The balance should be visible in the main application window. Do not worry about different
starting currencies, if the user starts with 100 and converts 10 of currency A to currency B, then
converts 20 of currency C to currency D, their balance should be 70
