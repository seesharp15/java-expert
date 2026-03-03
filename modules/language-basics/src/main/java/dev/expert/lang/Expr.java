package dev.expert.lang;

sealed public interface Expr permits Lit, Add, Negate { }

