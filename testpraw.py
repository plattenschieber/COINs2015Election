import praw
from pprint import pprint

# setup a unique user agent to not get banned
r = praw.Reddit(user_agent='mac:COINs2015Election:v1.0.1 (by /u/plattenschieber)')
# this is the subreddit we are interested in
# TODO should be a dynamical list based on buzzwords and some manual ones where we know they are relevant for us 
subreddit = r.get_subreddit('SandersForPresident')

# get last 1000 comments in subreddit 'SandersForPresident'
subreddit_comments = subreddit.get_comments(limit=1000)
# count total relevant answers
count=0
# flattening the tree since we don't care about the answering order
flat_comments = praw.helpers.flatten_tree(subreddit_comments)
for comment in flat_comments:
    # test if it contains relevant information about a candidate
    buzzwords = ["sander", "bernie", "sanders"]
    if any(buzz in comment.body.lower() for buzz in buzzwords):
        count = count + 1
        # print only first 100 character (for more look at comment.body)
        print(comment)
    print("Number of comments including buzzwords: ", i)
