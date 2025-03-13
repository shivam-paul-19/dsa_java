import java.util.*;

public class desTwitter {
    class User {
        int userId;
        List<Integer> tweets;
        HashSet<Integer> followers;

        User(int userId) {
            this.userId = userId;
            this.followers = new HashSet<>();
            this.tweets = new ArrayList<>();
        }
    }

    class Twitter {
        HashMap<Integer, User> users;
        public Twitter() {
            this.users = new HashMap<>();
        }
        
        public void postTweet(int userId, int tweetId) {
            if(!users.containsKey(userId)) {
                users.put(userId, new User(userId));
                users.get(userId).tweets.add(tweetId);
                return;
            } 
            
            users.get(userId).tweets.add(tweetId);
        }
        
        public List<Integer> getNewsFeed(int userId) {
            if(!users.containsKey(userId)) {
                return new ArrayList<>();
            }
            List<Integer> feed = new ArrayList<>();

            int i=0;
            while(i<users.get(userId).tweets.size() && i<10) {
                feed.add(users.get(userId).tweets.get(i));
            }

            return feed;
        }
        
        public void follow(int followerId, int followeeId) {
            if(!users.containsKey(followerId)) {
                users.put(followerId, new User(followerId));
            }

            if(!users.containsKey(followeeId)) {
                users.put(followeeId, new User(followeeId));
            }

            users.get(followeeId).followers.add(followerId);
        }
        
        public void unfollow(int followerId, int followeeId) {
            if(!users.containsKey(followeeId) || !users.containsKey(followerId)) {
                return;
            }
            
            users.get(followeeId).followers.remove(followerId);
        }
    }
}
