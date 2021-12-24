package me.nevzatcirak.service.approval.controller.graphql.config;

import me.nevzatcirak.service.approval.controller.graphql.ProcessMutationResolver;
import me.nevzatcirak.service.approval.controller.graphql.ProcessQueryResolver;
import me.nevzatcirak.service.approval.controller.graphql.resolver.MutationResolver;
import me.nevzatcirak.service.approval.controller.graphql.resolver.QueryResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nevzat ÇIRAK,
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 12/24/2021.
 */
@Configuration
public class BeanDecoration {
    @Bean
    public ProcessQueryResolver queryResolver() {
        return new QueryResolver();
    }

    @Bean
    public ProcessMutationResolver mutationResolver() {
        return new MutationResolver();
    }
}
