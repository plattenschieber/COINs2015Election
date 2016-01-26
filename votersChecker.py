import csv
import sys

def query_yes_no(question, default="yes"):
    """Ask a yes/no question via raw_input() and return their answer.

    "question" is a string that is presented to the user.
    "default" is the presumed answer if the user just hits <Enter>.
        It must be "yes" (the default), "no" or None (meaning
        an answer is required of the user).

    The "answer" return value is True for "yes" or False for "no".
    """
    valid = {"yes": True, "y": True, "ye": True,
             "no": False, "n": False}
    if default is None:
        prompt = " [y/n] "
    elif default == "yes":
        prompt = " [Y/n] "
    elif default == "no":
        prompt = " [y/N] "
    else:
        raise ValueError("invalid default answer: '%s'" % default)

    while True:
        sys.stdout.write(question + prompt)
        choice = raw_input().lower()
        if default is not None and choice == '':
            return valid[default]
        elif choice in valid:
            return valid[choice]
        else:
            sys.stdout.write("Please respond with 'yes' or 'no' "
                             "(or 'y' or 'n').\n")

def buzzwordmarker(body):
    # setup buzzwords
    buzzwords = ['regulators', 'elect', 'vote','elected','voted','potus','hope','confess','love', 'support', 'hillary','clinton','bernie','sanders','donald','trump','marco','rubio','ben','carson']
    # lower all words to make the buzzwords more visible (ok and to ease the replace method without using a fancy library)
    body = body.lower()
    # make some buzz appear to visualize the key words in the redditors comment
    for buzz in buzzwords:
        body = body.replace(buzz, "----"+buzz.upper()+"---")
    return body

def checkComment():
    with open('/Users/hans/Downloads/voters.csv') as csvfile, open('votersYes.csv','a') as csvfilewrite:
        reader = csv.DictReader(csvfile)
        fieldnames = ['author', 'subreddit', 'subreddit_id', 'created_utc', 'body']
        writer = csv.DictWriter(csvfilewrite, fieldnames=fieldnames)
        writer.writeheader()


        for row in reader:
            print(chr(27) + "[2J")
            print(buzzwordmarker(row['body']))
            print("\nsubreddit: " + row['subreddit'])
            print("author: " + row['author'])

            if query_yes_no("Is this a voter?"):
                writer.writerow({'author': row['author'], 'subreddit':row['subreddit'], 'subreddit_id':row['subreddit_id'], 'created_utc':row['created_utc'], 'body':row['body']})

checkComment()
