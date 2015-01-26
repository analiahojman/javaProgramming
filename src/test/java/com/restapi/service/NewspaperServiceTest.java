package com.restapi.service;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.restapi.model.Newspaper;
import com.restapi.model.Post;
import com.restapi.repository.NewspaperRepository;
import com.restapi.repository.PostRepository;

/**
 * This class tests all the newspaper services
 * @author analia.hojman
 */
public class NewspaperServiceTest {
	
	private NewspaperRepository newspaperRepositoryMock;
	private PostRepository postRepositoryMock;
	private NewspaperService newspaperService;

	@Before
	public void setUp() {
		newspaperRepositoryMock = mock(NewspaperRepository.class);
		postRepositoryMock = mock(PostRepository.class);
		newspaperService = new NewspaperService(newspaperRepositoryMock,postRepositoryMock);
	}

	/**
	 * This test verifies that a new newspaper was created and saved
	 */
	@Test
	public void addNewNewspaper() {

		when(newspaperRepositoryMock.save(any(Newspaper.class))).thenReturn(new Newspaper());
		newspaperService.create(new Newspaper());

		//verify that the newspaper repository has performed a save action to grab the new newspaper
		verify(newspaperRepositoryMock).save(any(Newspaper.class));
		verifyNoMoreInteractions(newspaperRepositoryMock);

	}

	/**
	 * This test verifies that a new post was added to a newspaper
	 */
	@Test
	public void addPostToNewspaper() {
		
		Newspaper newspaper = new Newspaper("Newspaper Name", "One Editorial Name", new Date(), new ArrayList<Post>());
		
		when(newspaperRepositoryMock.save(any(Newspaper.class))).thenReturn(newspaper);
		when(newspaperRepositoryMock.findById(any(Long.class))).thenReturn(newspaper);
		when(postRepositoryMock.save(any(Post.class))).thenReturn(new Post());
		
		newspaperService.addPost(new Long(1), any(Post.class));
	
		//verify that the post repository has performed a save action to grab the new post
		verify(postRepositoryMock).save(any(Post.class));
		
		//verify that the newspaper repository has performed a save action to grab the newspaper with new post
		verify(newspaperRepositoryMock).save(any(Newspaper.class));

		//verify that the newspaper list of posts is not empty
		assertFalse(newspaper.getPosts().isEmpty());
		
	}
	
	/**
	 * This test verifies that two new newspapers were created and saved
	 */
	@Test
	public void addTwoNewNewspapers(){
		
		when(newspaperRepositoryMock.save(any(Newspaper.class))).thenReturn(new Newspaper());
		
		// Create first newspaper
		newspaperService.create(new Newspaper("newspaper1", "editorial1", new Date()));
		
		// Create second newspaper
		newspaperService.create(new Newspaper("newspaper2", "editorial2", new Date()));
		
		//verify that the newspaper repository has performed two times the save action to grab both newspapers
	    verify(newspaperRepositoryMock, times(2)).save(any(Newspaper.class));
		verifyNoMoreInteractions(newspaperRepositoryMock);
	}
	
	
	
	

}
