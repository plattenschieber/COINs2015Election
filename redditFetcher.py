import praw
import csv
from pprint import pprint

# setup a unique user agent to not get banned
r = praw.Reddit(user_agent='mac:COINs2015Election:v1.0.2 (by /u/plattenschieber)')

# a list of subreddits based on buzzwords and some manual ones where we know they are relevant for us 
subredditlist = ['SandersForPresident', 'Clinton', 'hillaryclinton', 'democrats', 'Libertarian', 'PoliticalDiscussion', 'worldpolitics', 'POLITIC', 'politics', 'SandersAlerts', 'uspolitics', 'Liberal', 'The_Donald', 'Conservative', 'Conservatives', 'Marco_Rubio', 'republicans', 'Republican', 'ElectionPolls', 'Forecast2016', 'BenCarson']
# a '+' between all subreddits lets us get all named subreddits at once
subreddit = r.get_subreddit('+'.join(subredditlist))

# get last 1000 comments in subreddit 'SandersForPresident'
subreddit_comments = subreddit.get_comments(limit=10000)

with open('comments.csv', 'w') as csvfile:
    # count total relevant answers
    count=0

    # define dictionary for csv header and write it
    fieldnames = ['author', 'created_utc', 'subreddit', 'subreddit_id', 'ups', 'downs', 'body']
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
    writer.writeheader()

    # flattening the comment tree since we don't care about the answering order
    flat_comments = praw.helpers.flatten_tree(subreddit_comments)

    # print only some specific comments
    for comment in flat_comments:
        # test if it contains relevant information about a candidate
        buzzwords = ['vote', 'voting', 'sander', 'sanders', 'bernie', 'hillary', 'clinton', 'donald', 'trump', 'marco', 'rubio', 'ben', 'carson', 'republican', 'republicans', 'conservative', 'libertarian', 'democrats']
        if any(buzz in comment.body.lower() for buzz in buzzwords):
            count = count + 1
            # print only first 100 character (for more look at comment.body)
            print(str(count) + ": " + str(comment))
            # and write relevant comment into csv 
            writer.writerow({'author':comment.author, 'created_utc':comment.created_utc, 'subreddit':comment.subreddit, 'subreddit_id':comment.subreddit_id, 'ups':comment.ups, 'downs':comment.downs, 'body':comment.body.encode('utf-8')})

print('Number of comments including buzzwords: ', count)
