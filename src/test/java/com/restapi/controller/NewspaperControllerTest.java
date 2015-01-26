package com.restapi.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.restapi.Application;
import com.restapi.model.Newspaper;
import com.restapi.model.Post;
import com.restapi.repository.NewspaperRepository;
import com.restapi.repository.PostRepository;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import java.io.IOException;


/**
 * This class tests all the HTTP request related to a Newspaper
 * @author analia.hojman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class NewspaperControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<Newspaper> newspaperList = new ArrayList<Newspaper>();

    private List<Post> postList = new ArrayList<Post>();

    @Autowired
    private NewspaperRepository newspaperRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {        
        
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    
    @Before
    public void setup() throws Exception {
    	
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        // delete all the newspapers and posts
        deleteAllNewspapersAndPosts();

        // Create posts
        createAndSavePosts();

        // Create newspapers
       createAndSaveNewspapers();

       //Adding posts to newspapers
        Newspaper newspaper1 = addPost1and2ToNewspaper1(); 
        this.newspaperList.add(newspaper1);
        
        Newspaper newspaper2 = newspaperRepository.findByName("newspaper2");
        this.newspaperList.add(newspaper2);
       
    }
    
    /**
     * Tests the newspaper creation. Performs a http request POST to the uri /newspaper
     * @throws Exception
     */
    @Test
    public void createNewspaper() throws Exception {
        String newspaperJson = json(new Newspaper());
        String uri = "/newspaper";
        this.mockMvc.perform(post(uri)
                .contentType(contentType)
                .content(newspaperJson))
                .andExpect(status().isCreated());
    }
    
    /**
     * Tests the newspaper reading. Performs a http request GET to the uri /newspaper/id
     * @throws Exception
     */
    @Test
    public void readNewspaper() throws Exception {
    	
    	//The expected newspaper
    	Newspaper newspaper = this.newspaperList.get(0);
    	// To read a newspaper, the url should be "/newspaper/id"
    	String uri = "/newspaper/"+newspaper.getId();
    	
    	//perform the get action and verify the results
        this.mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                //verify newspaper attributes 
                .andExpect(jsonPath("$.name", is(newspaper.getName())))
                .andExpect(jsonPath("$.printingPaperType", is(newspaper.getPrintingPaperType().toString())))
                .andExpect(jsonPath("$.editorialName", is(newspaper.getEditorialName())))
                //verify newspaper's post1
                .andExpect(jsonPath("$.posts[0].title", is(newspaper.getPosts().get(0).getTitle()))) 
                .andExpect(jsonPath("$.posts[0].author", is(newspaper.getPosts().get(0).getAuthor()))) 
                .andExpect(jsonPath("$.posts[0].body", is(newspaper.getPosts().get(0).getBody())))
                //verify newspaper's post2
        		.andExpect(jsonPath("$.posts[1].title", is(newspaper.getPosts().get(1).getTitle()))) 
        		.andExpect(jsonPath("$.posts[1].author", is(newspaper.getPosts().get(1).getAuthor()))) 
        		.andExpect(jsonPath("$.posts[1].body", is(newspaper.getPosts().get(1).getBody())));
        
    }
    
    /**
     * Tests the adding of a new post to a newspaper. Performs a http request POST to the uri /newspaper/id/post
     * @throws Exception
     */
    @Test
    public void addPostToNewspaper() throws Exception{
    	
    	Newspaper newspaper = this.newspaperList.get(1);
    	//To add a new post to the newspaper, the url should be "/newspaper/id/post"
    	String uri = "/newspaper/"+ newspaper.getId() + "/post/";
    	
    	//The new post to be added to the newspaper in json format
    	String postJson = json(new Post("a1","t1","b1"));
    	
        this.mockMvc.perform(post(uri)
                .contentType(contentType)
                .content(postJson))
                .andExpect(status().isCreated())
                
                //verify that the post was added to the returned newspaper
                .andExpect(jsonPath("$.posts[0].title", is("t1")))
        		.andExpect(jsonPath("$.posts[0].author", is("a1")))
                .andExpect(jsonPath("$.posts[0].body", is("b1")));
    	

    }
    
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
    
    protected void createAndSavePosts(){
    	this.postList.add(postRepository.save(new Post("Autor 1","Title 1","Body 1")));
        this.postList.add(postRepository.save(new Post("Autor 2","Title 2","Body 2")));
    }
    
    protected void createAndSaveNewspapers(){
    	 newspaperRepository.save(new Newspaper("newspaper1","editorial1",new Date()));
         newspaperRepository.save(new Newspaper("newspaper2","editorial2",new Date()));
    }
    
    protected void deleteAllNewspapersAndPosts(){
        this.newspaperRepository.deleteAllInBatch();
        this.postRepository.deleteAllInBatch();
    }
    
    protected Newspaper addPost1and2ToNewspaper1(){
    	
        Newspaper newspaper1 = newspaperRepository.findByName("newspaper1");
        
        List<Post> posts = Arrays.asList(postRepository.findByTitle("Title 1"),postRepository.findByTitle("Title 2"));
        newspaper1.setPosts(posts);
        newspaperRepository.save(newspaper1); 
        
        return newspaper1;
    }
    
 }
