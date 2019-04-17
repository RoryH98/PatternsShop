package com.roryharford.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roryharford.user.User;
import com.roryharford.user.UserRepository;

@Service
public class CommentService {

	// links it with the customerRepository
	@Autowired
	private CommentRepository commentRepository;
	
	private ArrayList<Comment> cart = new ArrayList<Comment>();
	
	public List<Comment> getAllcomments() {
		// connects to the database and runs a query
		List<Comment> comments = new ArrayList<>();
		// adds each User into the array
		commentRepository.findAll().forEach(comments::add);
		return comments;
	}

	public Comment getComment(int id) {
		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		// It knows the id is a String because we set it in the User class
		return commentRepository.getOne( id);
	}
	

//	public User getUserByEmail(String email) {
//		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//		// It knows the id is a String because we set it in the User class
//		return customerRepository.getOne(email);
//	}

	public void addComment(Comment Comment) {
		commentRepository.save(Comment);
	}

	public void updateComment(String id, Comment Comment) {
		// A save can update and add a User because the User has information about what
		// it is an a repository can check if it already exists or not.
		commentRepository.save(Comment);
	}
	
	public void addCommentToCart(Comment Comment)
	{
		cart.add(Comment);
	}
	

	public Comment getCartComment(int i) {
		
		return this.cart.get(i);
	}


//	public void deleteUser(int id) {
//		customerRepository.deleteById( id);
//	}
	
}
