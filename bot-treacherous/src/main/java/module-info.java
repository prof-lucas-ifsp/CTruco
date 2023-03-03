import com.bueno.impl.treacherous.TreacherousBot;

module bot.treacherous {
    requires bot.spi;
    exports com.bueno.impl.treacherous;
    provides com.bueno.spi.service.BotServiceProvider with TreacherousBot;
}