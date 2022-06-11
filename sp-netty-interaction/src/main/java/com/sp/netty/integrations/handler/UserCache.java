package com.sp.netty.integrations.handler;

import io.netty.util.Recycler;
import org.hibernate.validator.internal.util.stereotypes.Immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [Add Description Here]
 *
 * @author luchao Created in 5/22/22 6:31 PM
 */
public class UserCache {
    private static final Recycler<User> userRecycler = new Recycler<User>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    static final class User{
        private String name;
        private Recycler.Handle<User> handle;

        public User(Recycler.Handle<User> handle) {
            this.handle = handle;
        }

        public void recycle(){
            handle.recycle(this);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

//    public static void main(String[] args) {
//        User user1 = userRecycler.get();
//        user1.setName("hello");
//        user1.recycle();
//
//        User user2 = userRecycler.get();
//        System.out.println(user2.getName());
//        System.out.println(user1 == user2);
//    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("a", "b", "c", "d");
        System.out.println(test.stream().collect(Collectors.joining(",")));
    }
}
