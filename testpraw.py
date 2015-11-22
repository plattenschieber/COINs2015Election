import praw
from pprint import pprint

r = praw.Reddit(user_agent='mac:COINs2015Election:v1.0.1 (by /u/plattenschieber)')
subreddit = r.get_subreddit('SandersForPresident')
for submission in subreddit.get_hot(limit=5):
    pprint(help(submissions))
