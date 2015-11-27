import praw
import csv
from pprint import pprint

# setup a unique user agent to not get banned
r = praw.Reddit(user_agent='mac:COINs2015Election:v1.0.2 (by /u/plattenschieber)')
# this is the subreddit we are interested in
# TODO should be a dynamical list based on buzzwords and some manual ones where we know they are relevant for us 
subreddit = r.get_subreddit('SandersForPresident')

# get last 1000 comments in subreddit 'SandersForPresident'
subreddit_comments = subreddit.get_comments(limit=1000)

with open('comments.csv', 'w') as csvfile:
    # count total relevant answers
    count=0
    # flattening the comment tree since we don't care about the answering order
    flat_comments = praw.helpers.flatten_tree(subreddit_comments)

    # print only some specific comments
    for comment in flat_comments:
        # test if it contains relevant information about a candidate
        buzzwords = ["sander", "bernie", "sanders"]
        if any(buzz in comment.body.lower() for buzz in buzzwords):
            count = count + 1
            # print only first 100 character (for more look at comment.body)
            print(str(count) + ": " + str(comment))

    # define dictionary for csv header and write it
    fieldnames = ['author', 'created_utc', 'subreddit', 'subreddit_id', 'ups', 'downs', 'body']
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
    writer.writeheader()

    # get out all comments into the csv file
    for comment in flat_comments:
        writer.writerow({'author':comment.author, 'created_utc':comment.created_utc, 'subreddit':comment.subreddit, 'subreddit_id':comment.subreddit_id, 'ups':comment.ups, 'downs':comment.downs, 'body':comment.body.encode('utf-8')})

print('Number of comments including buzzwords: ', count)
