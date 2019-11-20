package com.jarqprog.web.config;

//@Configuration
//@EnableWebMvc
public class ConfigWebModule {//implements WebMvcConfigurer {// ApplicationContextAware,


//    static {
//        logger.warn("***********************************************************************************************");
//        logger.warn("***********************************************************************************************");
//        logger.warn("ConfigWebModule");
//        logger.warn("***********************************************************************************************");
//        logger.warn("***********************************************************************************************");
//    }
//
//
////    private ApplicationContext applicationContext;
//    private final FormattingConversionService mvcConversionService;
//
//    @Autowired
//    public ConfigWebModule(@NonNull ApplicationContext applicationContext,
//                           @NonNull FormattingConversionService mvcConversionService) {
//        this.applicationContext = applicationContext;
//        this.mvcConversionService = mvcConversionService;
//    }
//
//    @Bean
//    protected ThymeleafViewResolver viewResolver(){
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//        return viewResolver;
//    }
//
//    @Bean
//    protected SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setEnableSpringELCompiler(true);
//        engine.setTemplateResolver(templateResolver());
//        engine.setDialects(thymeleafDialects());
//        return engine;
//    }
//
//    private ITemplateResolver templateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("classpath:/WEB-INF/templates/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        logger.warn("***********************************************************************************************");
//        logger.warn("***********************************************************************************************");
//        logger.warn("ConfigWebModule");
//        logger.warn("***********************************************************************************************");
//        logger.warn("***********************************************************************************************");
//        logger.warn(resolver.getName());
//        logger.warn(resolver.getPrefix());
//
//        return resolver;
//    }
//
//    @Bean
//    protected DomainClassConverter<?> domainClassConverter() {
//        return new DomainClassConverter<>(mvcConversionService);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(
//                "/webjars/**",
//                "/img/**",
//                "/css/**",
//                "/js/**")
//                .addResourceLocations(
//                        "classpath:/META-INF/resources/webjars/",
//                        "classpath:/WEB-INF/static/img/",
//                        "classpath:/WEB-INF/static/css/",
//                        "classpath:/WEB-INF/static/js/");
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Bean
//    protected Set<IDialect> thymeleafDialects() {
//        Set<IDialect> dialects = new HashSet<>();
//        dialects.add(new SpringStandardDialect());
//        dialects.add(new LayoutDialect());
//        dialects.add(new SpringSecurityDialect());
//        return dialects;
//    }
//
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
//
//    public static DomainUser getDummyUser() {
//        final var mock = "mock";
//        final var contact = ContactEntity.fromContact(DomainContact.createWith()
//                .email("username@gmail.com")
//                .firstName(mock)
//                .build());
//        final var user = DomainUser.createWith().login(mock).password(mock).contact(contact).build();
//        logger.info("Dummy user created: {}", user);
//        return user;
//    }
}
